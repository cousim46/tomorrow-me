package tomorrowme.todo.api.service.work;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import tomorrowme.todo.api.service.work.dto.response.BoxInfo;
import tomorrowme.todo.domain.account.Account;
import tomorrowme.todo.domain.account.AccountRepository;
import tomorrowme.todo.domain.work.Box;
import tomorrowme.todo.domain.work.BoxRepository;
import tomorrowme.todo.exception.TomorrowException;

@SpringBootTest
class BoxReadServiceTest {

    @Autowired
    private BoxReadService boxReadService;
    @Autowired
    private BoxRepository boxRepository;
    @Autowired
    private AccountRepository accountRepository;

    @AfterEach
    void clear() {
        boxRepository.deleteAllInBatch();
        accountRepository.deleteAllInBatch();
    }

    @DisplayName("보관함을 최신순으로 모두 조회한다.")
    @Test
    void findAll() {
        //given
        String phone = "01012341234";
        String keyword = "keyword";
        LocalTime wakeUpTime = LocalTime.of(9, 0, 0);
        LocalTime sleepTime = LocalTime.of(23, 0, 0);
        Account savedAccount = accountRepository.save(createAccount(phone, keyword,wakeUpTime,sleepTime));

        LocalDate firstBox = LocalDate.of(2024, 5, 1);
        LocalDate secondBox = LocalDate.of(2024, 5, 2);
        Box box1 = createBox("첫번째", savedAccount, firstBox);
        Box box2 = createBox("두번째", savedAccount, secondBox);
        boxRepository.saveAll(List.of(box1, box2));

        //when
        List<BoxInfo> boxInfos = boxReadService.findAll(savedAccount.getPhone(),
            savedAccount.getKeyword());

        //then
        assertThat(boxInfos).hasSize(2)
            .extracting("createdAt","title")
            .containsExactly(
                Tuple.tuple(box2.getCreatedAt(),box2.getTitle()),
                Tuple.tuple(box1.getCreatedAt(),box1.getTitle())
            );

    }
    @DisplayName("보관함을 조회할 회원이 존재하지 않으면 예외가 발생한다.")
    @Test
    void occurFindByAccountNotExistException() {
        //given
        String phone = "01012341234";
        String keyword = "keyword";
        Account savedAccount = null;


        LocalDate firstBox = LocalDate.of(2024, 5, 1);
        LocalDate secondBox = LocalDate.of(2024, 5, 2);
        Box box1 = createBox("첫번째", savedAccount, firstBox);
        Box box2 = createBox("두번째", savedAccount, secondBox);
        boxRepository.saveAll(List.of(box1, box2));

        //when
        TomorrowException tomorrowException = assertThrows(TomorrowException.class,
            () -> boxReadService.findAll(phone, keyword));

        //then
        assertThat(tomorrowException.getExceptionMessage()).isEqualTo("존재 하지 않는 정보입니다.");
        assertThat(tomorrowException.getExceptionStatus()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    private Account createAccount(String phone, String keyword, LocalTime wakeUpTime, LocalTime sleepTime) {
        return Account.singUp(phone, keyword, wakeUpTime, sleepTime);
    }
    private Box createBox(String title, Account account, LocalDate registerDate) {
        return Box.create(title, account, registerDate);
    }
}
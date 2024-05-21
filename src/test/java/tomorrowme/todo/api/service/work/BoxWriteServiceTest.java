package tomorrowme.todo.api.service.work;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import tomorrowme.todo.domain.account.Account;
import tomorrowme.todo.domain.account.AccountRepository;
import tomorrowme.todo.domain.work.Box;
import tomorrowme.todo.domain.work.BoxRepository;
import tomorrowme.todo.exception.TomorrowException;

@SpringBootTest
class BoxWriteServiceTest {

    @Autowired
    private BoxWriteService boxWriteService;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private BoxRepository boxRepository;

    @AfterEach
    void clean() {
        boxRepository.deleteAllInBatch();
        accountRepository.deleteAllInBatch();
    }

    @DisplayName("회원은 할 일 보관함을 만들 수 있다.")
    @Test
    void create() {
        //given
        String phone = "01012341234";
        String keyword = "keyword";
        String title = "개발 공부";
        LocalTime wakeUpTime = LocalTime.of(9, 0, 0);
        LocalTime sleepTime = LocalTime.of(23, 0, 0);
        LocalDateTime todayDate = LocalDateTime.now();
        accountRepository.save(
            Account.singUp(phone, keyword, wakeUpTime, sleepTime));

        //when
        boxWriteService.create(phone, keyword, title, todayDate);

        //then
        List<Box> boxes = boxRepository.findAll();

        assertThat(boxes).hasSize(1);
    }
    @DisplayName("보관함을 생성 하는 회원이 존재하지 않으면 예외가 발생한다.")
    @Test
    void occurCreateBoxWithoutAccountException() {
        //given
        String phone = "01012341234";
        String keyword = "keyword";
        String title = "개발 공부";
        LocalDateTime todayDate = LocalDateTime.now();

        //when
        TomorrowException tomorrowException = assertThrows(TomorrowException.class,
            () -> boxWriteService.create(phone, keyword, title, todayDate));

        //then
        assertThat(tomorrowException.getExceptionMessage()).isEqualTo("존재 하지 않는 정보입니다.");
        assertThat(tomorrowException.getExceptionStatus()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @DisplayName("하루에 두개 이상의 보관함을 생성하면 예외가 발생한다.")
    @Test
    void occurOneMoreCreateBoxException() {
        //given
        String phone = "01012341234";
        String keyword = "keyword";
        String title = "개발 공부";
        LocalTime wakeUpTime = LocalTime.of(9, 0, 0);
        LocalTime sleepTime = LocalTime.of(23, 0, 0);
        LocalDateTime todayDate = LocalDateTime.now();
        accountRepository.save(
            Account.singUp(phone, keyword, wakeUpTime, sleepTime));

        boxWriteService.create(phone, keyword, title, todayDate);

        //when
       DataIntegrityViolationException dataIntegrityViolationException = assertThrows(
            DataIntegrityViolationException.class,
            () -> boxWriteService.create(phone, keyword, title, todayDate));

        //then
        assertThat(dataIntegrityViolationException.getMessage()).isEqualTo("이미 등록된 보관함이 존재합니다.");
    }
}
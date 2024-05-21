package tomorrowme.todo.domain.work;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.assertj.core.data.Index;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import tomorrowme.todo.config.AuditingConfig;
import tomorrowme.todo.domain.account.Account;
import tomorrowme.todo.domain.account.AccountRepository;

@DataJpaTest
@Import(value = AuditingConfig.class)
class BoxRepositoryTest {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private BoxRepository boxRepository;

    @DisplayName("보관함을 최신순으로 정렬하여 모든 보관함을 조회한다.")
    @Test
    void findAllByAccountOrderByRegistrationDateDesc() {
        //given
        String phone = "01012341234";
        String keyword = "keyword";
        LocalTime wakeUpTime = LocalTime.of(9, 0, 0);
        LocalTime sleepTime = LocalTime.of(23, 0, 0);
        Account savedAccount = accountRepository.save(
            Account.singUp(phone, keyword, wakeUpTime, sleepTime));
        LocalDateTime firstBox = LocalDateTime.of(2024, 5, 1, 1, 0, 0);
        LocalDateTime secondBox = LocalDateTime.of(2024, 5, 2, 1, 0, 0);
        LocalDateTime thirdBox  = LocalDateTime.of(2024, 5, 1, 2, 0, 0);

        Box box1 = Box.create("첫번째", savedAccount, firstBox);
        Box box2 = Box.create("두번째", savedAccount, secondBox);
        Box box3 = Box.create("세번째", savedAccount, thirdBox);
        boxRepository.saveAll(List.of(box1, box2, box3));

        //when
        List<Box> boxes = boxRepository.findAllByAccountOrderByRegistrationDateDesc(
            savedAccount);

        //then
        assertThat(boxes).hasSize(3)
            .extracting("registrationDate","title")
            .containsExactly(
                Tuple.tuple(secondBox,box2.getTitle()),
                Tuple.tuple(thirdBox,box3.getTitle()),
                Tuple.tuple(firstBox,box1.getTitle())
                );
    }
}
package tomorrowme.todo.domain.account;

import java.time.LocalTime;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import tomorrowme.todo.config.AuditingConfig;

@DataJpaTest
@Import(value = AuditingConfig.class)
class AccountRepositoryTest {

    @Autowired
    private AccountRepository accountRepository;

    @DisplayName("전화번호와 키워드를 통해 회원을 찾을 수 있다.")
    @Test
    void findByPhoneAndKeyword() {
        //given
        String phone = "01012341234";
        String keyword = "keyword";
        LocalTime wakeUpTime = LocalTime.of(9, 0, 0);
        LocalTime sleepTime = LocalTime.of(23, 0, 0);
        Account savedAccount = accountRepository.save(
            Account.singUp(phone, keyword, wakeUpTime, sleepTime));

        //when
        Account findAccount = accountRepository.findByPhoneAndKeyword(phone, keyword).get();

        //then
        Assertions.assertThat(findAccount).isEqualTo(savedAccount);
        Assertions.assertThat(findAccount.getPhone()).isEqualTo(savedAccount.getPhone());
        Assertions.assertThat(findAccount.getKeyword()).isEqualTo(savedAccount.getKeyword());
        Assertions.assertThat(findAccount.getWakeUpTime()).isEqualTo(savedAccount.getWakeUpTime());
        Assertions.assertThat(findAccount.getSleepTime()).isEqualTo(savedAccount.getSleepTime());
    }
}
package tomorrowme.todo.api.service.account;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalTime;
import java.util.List;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import tomorrowme.todo.api.service.account.AccountWriteService;
import tomorrowme.todo.domain.account.Account;
import tomorrowme.todo.domain.account.AccountRepository;

@SpringBootTest
class AccountWriteServiceTest {

    @Autowired
    private AccountWriteService accountWriteService;

    @Autowired
    private AccountRepository accountRepository;

    @AfterEach
    void clear() {
        accountRepository.deleteAllInBatch();
    }

    @DisplayName("전화번호와 키워드를 통해서 회원가입이 가능하다.")
    @Test
    void signUp() {
        //given
        String phone = "01012341234";
        String keyword = "test";
        LocalTime wakeUpTime = LocalTime.of(9,0,0);
        LocalTime sleepTime = LocalTime.of(2,0,0);

        //when
        accountWriteService.singUp(phone, keyword, wakeUpTime, sleepTime);

        //then
        List<Account> accounts = accountRepository.findAll();

        //then
        assertThat(accounts).hasSize(1)
                .extracting("phone", "keyword")
                    .contains(Tuple.tuple(phone, keyword));
    }

    @DisplayName("이미 저장된 번호로 회원가입 할 경우 예외가 발생한다.")
    @Test
    void test() {
        //given
        String phone = "01012341234";
        String keyword = "test";
        LocalTime wakeUpTime = LocalTime.of(9,0,0);
        LocalTime sleepTime = LocalTime.of(2,0,0);
        accountWriteService.singUp(phone, keyword, wakeUpTime, sleepTime);

        //when && then
        assertThatThrownBy(() -> accountWriteService.singUp(phone, keyword, wakeUpTime, sleepTime))
            .isInstanceOf(DataIntegrityViolationException.class)
            .hasMessage("이미 존재하는 정보입니다.");
    }
}
package tomorrowme.todo.domain.account;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AccountTest {

    @DisplayName("핸드폰 번호와 키워드, 기상시간, 취침시간 데이터로 회원을 생성한다.")
    @Test
    void signUp() {
        //given
        String phone = "01012341234";
        String keyword = "keyword";
        LocalTime wakeUpTime = LocalTime.of(9,0,0);
        LocalTime sleepTime = LocalTime.of(2,0,0);

        //when
        Account account = Account.singUp(phone, keyword, wakeUpTime, sleepTime);

        //then
        assertThat(account).isNotNull();
        assertThat(account.getKeyword()).isEqualTo(keyword);
        assertThat(account.getPhone()).isEqualTo(phone);
    }
}
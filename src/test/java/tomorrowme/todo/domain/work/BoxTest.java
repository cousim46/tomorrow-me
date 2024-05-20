package tomorrowme.todo.domain.work;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tomorrowme.todo.domain.account.Account;

class BoxTest {

    @DisplayName("회원은 할 일을 작성할 보관함을 만들 수 있다.")
    @Test
    void createBox() {
        //given
        String phone = "01012341234";
        String keyword = "keyword";
        LocalTime wakeUpTime = LocalTime.of(9,0,0);
        LocalTime sleepTime = LocalTime.of(2,0,0);
        Account account = Account.singUp(phone, keyword, wakeUpTime, sleepTime);

        //when
        Box box = Box.create(account);

        //then
        assertThat(box.getAccount()).isEqualTo(account);
    }

}
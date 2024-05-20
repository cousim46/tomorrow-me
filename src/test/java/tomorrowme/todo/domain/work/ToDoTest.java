package tomorrowme.todo.domain.work;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tomorrowme.todo.domain.account.Account;

class ToDoTest {

    @DisplayName("회원은 보관함에서 할 일을 만들 수 있다.")
    @Test
    void create() {
        //given
        String phone = "01012341234";
        String keyword = "keyword";
        LocalTime wakeUpTime = LocalTime.of(9,0,0);
        LocalTime sleepTime = LocalTime.of(2,0,0);
        Account account = Account.singUp(phone, keyword, wakeUpTime, sleepTime);
        Box box = Box.create(account);
        int orders = 0;
        String content = "JPA 공부";
        //when
        ToDo todo = ToDo.create(orders, content, box);

        //then
        assertThat(todo.getBox()).isEqualTo(box);
        assertThat(todo.getBox().getAccount()).isEqualTo(account);
        assertThat(todo.getOrders()).isEqualTo(orders);
        assertThat(todo.getContent()).isEqualTo(content);

    }

}
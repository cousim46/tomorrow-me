package tomorrowme.todo.domain.account;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AccountTest {

    @DisplayName("전화번호, 비밀번호, 패스워드를 통해 사용자를 생성한다.")
    @Test
    void singUp() {
        //given
        String phone = "01011112222";
        String password = "test";
        String salt = "salt";

        //when
        Account account = Account.singUp(phone, password, salt);

        //then
        assertThat(account.getPhone()).isEqualTo(phone);
        assertThat(account.getPassword()).isEqualTo(password);
        assertThat(account.getSalt()).isEqualTo(salt);
    }
}
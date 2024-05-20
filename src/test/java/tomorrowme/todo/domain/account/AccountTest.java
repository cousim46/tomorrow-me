package tomorrowme.todo.domain.account;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AccountTest {

    @DisplayName("핸드폰 번호와 키워드로 회원을 생성한다.")
    @Test
    void signUp() {
        //given
        String phone = "01012341234";
        String keyword = "keyword";

        //when
        Account account = Account.singUp(phone, keyword);

        //then
        assertThat(account).isNotNull();
        assertThat(account.getKeyword()).isEqualTo(keyword);
        assertThat(account.getPhone()).isEqualTo(phone);
    }
}
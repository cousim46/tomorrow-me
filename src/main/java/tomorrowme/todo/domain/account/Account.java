package tomorrowme.todo.domain.account;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import tomorrowme.todo.domain.BaseEntity;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Account extends BaseEntity {

    @Column(nullable = false)
    private String phone;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String salt;

    private Account(String phone, String password, String salt) {
        this.phone = phone;
        this.password = password;
        this.salt = salt;
    }
    public static Account singUp(String phone, String encryptPassword, String salt) {
        return new Account(phone, encryptPassword, salt);
    }
}

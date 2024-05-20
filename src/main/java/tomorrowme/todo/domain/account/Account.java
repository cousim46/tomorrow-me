package tomorrowme.todo.domain.account;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import tomorrowme.todo.domain.BaseEntity;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(
    uniqueConstraints = @UniqueConstraint(
        name = "phone",
        columnNames = "phone"
    )
)
public class Account extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String phone;
    @Column(nullable = false)
    private String keyword;

    private Account(String phone, String keyword) {
        this.phone = phone;
        this.keyword = keyword;

    }

    public static Account singUp(String phone, String keyword) {
        return new Account(phone,keyword);
    }
}

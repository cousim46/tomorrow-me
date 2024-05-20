package tomorrowme.todo.domain.work;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import tomorrowme.todo.domain.BaseEntity;
import tomorrowme.todo.domain.account.Account;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Box extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    private Account account;

    private Box(Account account) {
        this.account = account;
    }

    public static Box create(Account account) {
        return new Box(account);
    }
}

package tomorrowme.todo.domain.work;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import tomorrowme.todo.domain.BaseEntity;
import tomorrowme.todo.domain.account.Account;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(
    uniqueConstraints = @UniqueConstraint(
        name = "box_created_at", columnNames = {"account_id", "registrationDate"}
    )
)
public class Box extends BaseEntity {
    private String title;
    private LocalDate registrationDate;

    @ManyToOne(fetch = FetchType.LAZY)
    private Account account;

    private Box(String title, Account account, LocalDate registrationDate) {
        this.title = title;
        this.account = account;
        this.registrationDate = registrationDate;
    }

    public static Box create(String title,Account account, LocalDate registrationDate) {
        return new Box(title,account, registrationDate);
    }
}

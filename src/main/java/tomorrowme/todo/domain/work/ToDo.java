package tomorrowme.todo.domain.work;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import tomorrowme.todo.domain.BaseEntity;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ToDo extends BaseEntity {

    private int orders;
    private boolean isComplete;

    @Column(nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    private Box box;

    private ToDo(int orders, boolean isComplete, String content, Box box) {
        this.orders = orders;
        this.isComplete = isComplete;
        this.content = content;
        this.box = box;
    }

    public static ToDo create(int orders, String content, Box box) {
        return new ToDo(orders, false, content, box);
    }
}

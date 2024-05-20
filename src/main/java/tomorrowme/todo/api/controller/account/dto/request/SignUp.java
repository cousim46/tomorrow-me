package tomorrowme.todo.api.controller.account.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalTime;
import lombok.AllArgsConstructor;
import lombok.Getter;

public record SignUp (
    @NotBlank(message = "핸드폰 번호는 비어있을 수 없습니다.") String phone,
    @NotBlank(message = "키워드는 비어있을 수 없습니다.")
    String keyword,
    @NotNull(message = "기상 시간을 필수로 입력해야 합니다.")
    LocalTime wakeUpTime,
    @NotNull(message = "취침 시간을 필수로 입력해야 합니다.")
    LocalTime sleepTime
){}

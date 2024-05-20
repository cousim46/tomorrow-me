package tomorrowme.todo.api.controller.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SignUp {
    @NotBlank(message = "핸드폰 번호는 비어있을 수 없습니다.")
    private String phone;
    @NotBlank(message = "키워드는 비어있을 수 없습니다.")
    private String keyword;
}

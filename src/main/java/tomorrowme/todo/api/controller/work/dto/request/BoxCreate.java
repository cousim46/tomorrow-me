package tomorrowme.todo.api.controller.work.dto.request;

import jakarta.validation.constraints.NotBlank;

public record BoxCreate (
     String title,
     @NotBlank(message = "전화번호는 필수로 입력해야 합나다.")
     String phone,
     @NotBlank(message = "키워드는 필수로 입력해야 합나다.")
     String keyword
){
}
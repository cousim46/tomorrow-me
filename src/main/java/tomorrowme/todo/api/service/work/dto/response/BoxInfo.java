package tomorrowme.todo.api.service.work.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;

public record BoxInfo(
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime registrationDate,
    String  title,
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime updatedAt
) {
}
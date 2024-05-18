package tomorrowme.todo.exception;

import java.time.LocalDateTime;
import lombok.*;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class TomorrowExceptionResponse {
    private String message;
    private String status;
    private LocalDateTime time;
    private int statusCode;

    public static TomorrowExceptionResponse response(TomorrowException exception) {
        return new TomorrowExceptionResponse(
            exception.getExceptionMessage(),
            exception.getExceptionStatus(),
            LocalDateTime.now(),
            exception.getExceptionStatusCode()
        );
    }

}

package tomorrowme.todo.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public class TomorrowException extends RuntimeException {
    private final TomorrowErrorCode tomorrowErrorCode;

    public String getExceptionMessage() {
        return tomorrowErrorCode.getMessage();
    }

    public HttpStatus getExceptionStatus() {
        return tomorrowErrorCode.getStatus();
    }
}

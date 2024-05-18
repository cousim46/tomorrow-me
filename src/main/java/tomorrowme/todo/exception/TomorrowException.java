package tomorrowme.todo.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class TomorrowException extends RuntimeException {
    private final TomorrowErrorCode tomorrowErrorCode;

    public String getExceptionMessage() {
        return tomorrowErrorCode.getMessage();
    }

    public String getExceptionStatus() {
        return tomorrowErrorCode.getStatus().name();
    }

    public int getExceptionStatusCode() {
        return tomorrowErrorCode.getStatus().value();
    }

}

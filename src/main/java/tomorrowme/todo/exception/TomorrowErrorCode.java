package tomorrowme.todo.exception;

import static org.springframework.http.HttpStatus.*;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum TomorrowErrorCode {
    ;
    private final HttpStatus status;
    private final String message;
}

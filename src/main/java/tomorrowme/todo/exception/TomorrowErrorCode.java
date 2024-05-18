package tomorrowme.todo.exception;

import static org.springframework.http.HttpStatus.*;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum TomorrowErrorCode {
    NOT_NULL_INFO(BAD_REQUEST, "필수 정보가 누락되었습니다.");

    private final HttpStatus status;
    private final String message;
}

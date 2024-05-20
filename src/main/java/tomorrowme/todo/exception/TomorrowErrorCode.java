package tomorrowme.todo.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum TomorrowErrorCode {
    NOT_EXIST_INFO(HttpStatus.BAD_REQUEST, "존재 하지 않는 정보입니다.");
    private final HttpStatus status;
    private final String message;
}

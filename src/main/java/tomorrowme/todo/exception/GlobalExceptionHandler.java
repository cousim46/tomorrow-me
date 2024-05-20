package tomorrowme.todo.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import tomorrowme.todo.api.controller.dto.response.CommonResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TomorrowException.class)
    public ResponseEntity<CommonResponse<Object>> responseTomorrowException(
        TomorrowException exception) {
        return new ResponseEntity<>(
            CommonResponse.of(exception.getExceptionStatus(), exception.getExceptionMessage()),
            exception.getExceptionStatus());
    }
}

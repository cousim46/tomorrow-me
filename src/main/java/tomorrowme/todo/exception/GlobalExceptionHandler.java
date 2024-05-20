package tomorrowme.todo.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
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

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<CommonResponse> dataIntegrityViolationException(DataIntegrityViolationException e) {
        return new ResponseEntity<>(
            CommonResponse.conflict(e.getMessage()), HttpStatus.CONFLICT
        );
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CommonResponse<Object>> methodArgumentNotValidException(MethodArgumentNotValidException e) {
        return new ResponseEntity<>(
            CommonResponse.badRequest(e.getFieldErrors().get(0).getDefaultMessage()), HttpStatus.BAD_REQUEST
        );
    }
}

package tomorrowme.todo.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import tomorrowme.todo.api.controller.account.dto.response.CommonResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TomorrowException.class)
    public ResponseEntity<CommonResponse> responseTomorrowException(
        TomorrowException exception) {
        return new ResponseEntity<>(
            CommonResponse.of(exception.getExceptionStatus(), exception.getExceptionMessage()),
            exception.getExceptionStatus());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<CommonResponse> dataIntegrityViolationException(DataIntegrityViolationException e) {
        return new ResponseEntity<>(
            CommonResponse.of(HttpStatus.CONFLICT,e.getMessage()),HttpStatus.CONFLICT
        );
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CommonResponse> methodArgumentNotValidException(MethodArgumentNotValidException e) {
        return new ResponseEntity<>(
            CommonResponse.of(HttpStatus.BAD_REQUEST,e.getFieldErrors().get(0).getDefaultMessage()), HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CommonResponse> exception() {
        return new ResponseEntity<>(CommonResponse.serverError(),HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

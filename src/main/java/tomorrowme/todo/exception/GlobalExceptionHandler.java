package tomorrowme.todo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TomorrowException.class)
    public ResponseEntity<TomorrowExceptionResponse> responseTomorrowException(TomorrowException exception) {
        TomorrowExceptionResponse response = TomorrowExceptionResponse.response(exception);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
    }
}

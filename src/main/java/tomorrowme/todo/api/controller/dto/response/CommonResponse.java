package tomorrowme.todo.api.controller.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class CommonResponse<T> {
    private final int code;
    private final HttpStatus status;
    private final String message;
    private T data;

    private CommonResponse(HttpStatus status, String message, T data) {
        this.code = status.value();
        this.status = status;
        this.message = message;
        this.data = data;
    }

    private CommonResponse(HttpStatus status, String message) {
        this.code = status.value();
        this.status = status;
        this.message = message;
    }


    public static <T>CommonResponse<T> of(HttpStatus status, String message, T data) {
        return new CommonResponse<>(status,message, data);
    }
    public static <T>CommonResponse<T> of(HttpStatus status, String message) {
        return of(status,message, null);
    }
    public static <T>CommonResponse<T> serverError() {
        return new CommonResponse<>(HttpStatus.INTERNAL_SERVER_ERROR, "서버 에러");
    }

}

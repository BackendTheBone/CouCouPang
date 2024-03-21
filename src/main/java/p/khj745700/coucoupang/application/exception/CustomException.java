package p.khj745700.coucoupang.application.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public abstract class CustomException extends RuntimeException{
    private ExceptionConstants exceptionConstants;

    protected CustomException(ExceptionConstants exceptionConstants) {
        this.exceptionConstants = exceptionConstants;
    }
}

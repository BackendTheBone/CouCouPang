package p.khj745700.coucoupang.application.exception;

import org.springframework.http.HttpStatus;

public enum ExceptionConstants {
    USER_NOT_FOUND("사용자를 찾을 수 없습니다", HttpStatus.NOT_FOUND),
    USER_DUPLICATE("이미 기존에 사용자가 존재합니다.", HttpStatus.FORBIDDEN);

    private final String message;
    private final HttpStatus status;
    ExceptionConstants(String exceptionMessage, HttpStatus status) {
        message = exceptionMessage;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getStatus() {
        return status;
    }
}

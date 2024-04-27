package p.khj745700.coucoupang.application.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ExceptionConstants {
    USER_NOT_FOUND("사용자를 찾을 수 없습니다", HttpStatus.NOT_FOUND),
    USER_DUPLICATE("이미 기존에 사용자가 존재합니다.", HttpStatus.FORBIDDEN),
    SESSION_VALUE_NOT_EXIST("세션이 불안정합니다.", HttpStatus.NOT_FOUND),
    PRODUCT_NOT_FOUND("상품을 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    PRODUCT_UNAVAILABLE("상품이 구매 불가합니다.", HttpStatus.BAD_REQUEST),
    PRODUCT_OUT_OF_STOCK("상품의 재고가 부족합니다.", HttpStatus.BAD_REQUEST),
    PAYMENT_FAILED("결제에 실패하였습니다.", HttpStatus.BAD_REQUEST);

    private final String message;
    private final HttpStatus status;

    ExceptionConstants(String exceptionMessage, HttpStatus status) {
        message = exceptionMessage;
        this.status = status;
    }

}

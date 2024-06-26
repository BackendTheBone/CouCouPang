package p.khj745700.coucoupang.application.exception;

import org.springframework.http.HttpStatus;

public enum ExceptionConstants {
    USER_NOT_FOUND("사용자를 찾을 수 없습니다", HttpStatus.NOT_FOUND),
    USER_DUPLICATE("이미 기존에 사용자가 존재합니다.", HttpStatus.FORBIDDEN),
    SESSION_VALUE_NOT_EXIST("세션이 불안정합니다.", HttpStatus.NOT_FOUND),
    PRODUCT_STOCK_IS_POOL("상품 수량이 부족해 판매를 개시할 수 없습니다.", HttpStatus.FORBIDDEN),
    PRODUCT_NOT_FOUND("상품을 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    PRODUCT_NOT_EQUALS_SELLER("판매자와 다릅니다.", HttpStatus.FORBIDDEN),
    PRODUCT_STATE_CANT_SELL("상품 재고가 부족해 판매할 수 없습니다.", HttpStatus.FORBIDDEN),
    PRODUCT_DESCRIPTION_NOT_FOUND("상품 설명을 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    ORDER_NOT_FOUND("주문을 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    ORDER_ITEM_NOT_FOUND("주문상품을 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    ORDER_ITEM_ALREADY_SHIPPED("주문상품이 이미 배송되었습니다.", HttpStatus.BAD_REQUEST),
    PAYMENT_FAILED("결제에 실패하였습니다.", HttpStatus.BAD_REQUEST);

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

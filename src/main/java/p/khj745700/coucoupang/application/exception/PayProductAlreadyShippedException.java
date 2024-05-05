package p.khj745700.coucoupang.application.exception;

public class PayProductAlreadyShippedException extends CustomException {
    public PayProductAlreadyShippedException() {
        super(ExceptionConstants.PAY_PRODUCT_ALREADY_SHIPPED);
    }
}

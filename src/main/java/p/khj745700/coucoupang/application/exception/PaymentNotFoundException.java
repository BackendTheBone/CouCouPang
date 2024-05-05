package p.khj745700.coucoupang.application.exception;

public class PaymentNotFoundException extends CustomException {
    public PaymentNotFoundException() {
        super(ExceptionConstants.PAYMENT_NOT_FOUND);
    }
}

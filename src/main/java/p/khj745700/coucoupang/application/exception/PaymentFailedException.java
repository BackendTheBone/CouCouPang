package p.khj745700.coucoupang.application.exception;

public class PaymentFailedException extends CustomException {

    public PaymentFailedException() {
        super(ExceptionConstants.PAYMENT_FAILED);
    }

}

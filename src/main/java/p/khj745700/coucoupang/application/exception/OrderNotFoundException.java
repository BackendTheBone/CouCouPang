package p.khj745700.coucoupang.application.exception;

public class OrderNotFoundException extends CustomException {
    public OrderNotFoundException() {
        super(ExceptionConstants.ORDER_NOT_FOUND);
    }
}

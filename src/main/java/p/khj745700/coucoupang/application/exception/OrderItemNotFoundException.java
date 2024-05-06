package p.khj745700.coucoupang.application.exception;

public class OrderItemNotFoundException extends CustomException {
    public OrderItemNotFoundException() {
        super(ExceptionConstants.ORDER_ITEM_NOT_FOUND);
    }
}

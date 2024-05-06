package p.khj745700.coucoupang.application.exception;

public class OrderItemAlreadyShippedException extends CustomException {
    public OrderItemAlreadyShippedException() {
        super(ExceptionConstants.ORDER_ITEM_ALREADY_SHIPPED);
    }
}

package p.khj745700.coucoupang.application.exception;

public class ProductStateCantSellException extends CustomException {
    public ProductStateCantSellException() {
        super(ExceptionConstants.PRODUCT_STATE_CANT_SELL);
    }
}

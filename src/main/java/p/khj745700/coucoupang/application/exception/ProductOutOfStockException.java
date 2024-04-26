package p.khj745700.coucoupang.application.exception;

public class ProductOutOfStockException extends CustomException {

    public ProductOutOfStockException() {
        super(ExceptionConstants.PRODUCT_OUT_OF_STOCK);
    }

}

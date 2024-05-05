package p.khj745700.coucoupang.application.exception;

public class ProductStockIsPoolException extends CustomException{
    public ProductStockIsPoolException() {
        super(ExceptionConstants.PRODUCT_STOCK_IS_POOL);
    }
}

package p.khj745700.coucoupang.application.exception;

public class ProductUnavailableException extends CustomException {

    public ProductUnavailableException() {
        super(ExceptionConstants.PRODUCT_UNAVAILABLE);
    }

}

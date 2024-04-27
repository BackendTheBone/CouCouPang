package p.khj745700.coucoupang.application.exception;

public class ProductNotFoundException extends CustomException {

    public ProductNotFoundException() {
        super(ExceptionConstants.PRODUCT_NOT_FOUND);
    }

}

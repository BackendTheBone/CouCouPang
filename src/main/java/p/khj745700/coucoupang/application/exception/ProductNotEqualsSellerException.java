package p.khj745700.coucoupang.application.exception;

public class ProductNotEqualsSellerException extends CustomException{
    public ProductNotEqualsSellerException() {
        super(ExceptionConstants.PRODUCT_NOT_EQUALS_SELLER);
    }
}

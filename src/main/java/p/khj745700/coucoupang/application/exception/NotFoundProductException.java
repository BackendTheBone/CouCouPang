package p.khj745700.coucoupang.application.exception;

public class NotFoundProductException extends CustomException{
    public NotFoundProductException() {
        super(ExceptionConstants.PRODUCT_NOT_FOUND);
    }
}

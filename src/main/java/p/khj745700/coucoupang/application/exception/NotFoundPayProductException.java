package p.khj745700.coucoupang.application.exception;

public class NotFoundPayProductException extends CustomException {
    public NotFoundPayProductException() {
        super(ExceptionConstants.PAY_PRODUCT_NOT_FOUND);
    }
}

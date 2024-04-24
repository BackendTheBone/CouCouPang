package p.khj745700.coucoupang.application.exception;

public class NotFoundProductDescriptionException extends CustomException{
    public NotFoundProductDescriptionException() {
        super(ExceptionConstants.PRODUCT_DESCRIPTION_NOT_FOUND);
    }
}

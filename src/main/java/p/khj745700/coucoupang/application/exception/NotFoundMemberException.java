package p.khj745700.coucoupang.application.exception;

public class NotFoundMemberException extends CustomException{

    public NotFoundMemberException() {
        super(ExceptionConstants.USER_NOT_FOUND);
    }
}

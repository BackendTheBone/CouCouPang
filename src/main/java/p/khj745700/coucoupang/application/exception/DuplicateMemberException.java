package p.khj745700.coucoupang.application.exception;

public class DuplicateMemberException extends CustomException{

    public DuplicateMemberException() {
        super(ExceptionConstants.USER_NOT_FOUND);
    }
}

package p.khj745700.coucoupang.application.exception;

public class SessionInfoNotFoundException extends CustomException{

    public SessionInfoNotFoundException() {
        super(ExceptionConstants.SESSION_VALUE_NOT_EXIST);
    }
}

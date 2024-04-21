package p.khj745700.coucoupang.application.dao;

import jakarta.servlet.http.HttpSession;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import p.khj745700.coucoupang.application.config.constant.SessionConstants;
import p.khj745700.coucoupang.application.exception.CustomException;
import p.khj745700.coucoupang.application.exception.SessionInfoNotFoundException;

import java.util.Optional;
import java.util.function.Function;

@RequiredArgsConstructor
@Component
@Slf4j
public class SessionDao {

    private final HttpSession httpSession;


    public void remove(SessionConstants sessionConstants) {
        httpSession.removeAttribute(sessionConstants.getValue());
    }

    public void removeAll() {
        httpSession.invalidate();
    }

    public void put(SessionConstants sessionConstants, String value) {
        httpSession.setAttribute(sessionConstants.getValue(), value);
    }

    public String getNotCheck(SessionConstants sessionConstants) {
        return getAttribute(sessionConstants);
    }

    public String getOrElseThrowException(SessionConstants sessionConstants) {
        String val = (String) httpSession.getAttribute(sessionConstants.getValue());
        if(val == null) {
            String username = getAttribute(SessionConstants.USER_ID);
            ExceptionHandler.NULL.apply(new String[]{username, sessionConstants.getValue()});
        }

        return val;
    }

    private String getAttribute(SessionConstants sessionConstants) {
        return (String)httpSession.getAttribute(sessionConstants.getValue());
    }

    private static class ExceptionHandler {
        private static final Function<String[], CustomException> NULL;

        static {
            NULL = (String[] values) -> {
                log.info("사용자 {} 가 없는 세션 {}을 요청하였습니다. ", values[0], values[1]);
                return new SessionInfoNotFoundException();
            };
        }

    }
}

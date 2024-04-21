package p.khj745700.coucoupang.application.service.member.impl;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import p.khj745700.coucoupang.application.config.constant.SessionConstants;
import p.khj745700.coucoupang.application.dao.MemberDao;
import p.khj745700.coucoupang.application.dao.SessionDao;
import p.khj745700.coucoupang.application.dto.request.member.LoginRequest;
import p.khj745700.coucoupang.application.service.member.ILoginService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoginService implements ILoginService {
    private final SessionDao sessionDao;
    private final MemberDao memberDao;

    @Override
    @Transactional(readOnly = true)
    public boolean login(LoginRequest loginRequest) {
        if(!memberDao.isVerificationUsernameAndPassword(loginRequest)) {
            setUserSession(loginRequest.getUserId());
            return true;
        }
        return false;
    }

    public void loginCheck() {
        sessionDao.getOrElseThrowException(SessionConstants.USER_ID);
    }

    @Override
    public void logout() {
        log.trace("remove session: {}", sessionDao.getNotCheck(SessionConstants.USER_ID));
        sessionDao.remove(SessionConstants.USER_ID);
    }


    private void setUserSession(String username) {
        sessionDao.put(SessionConstants.USER_ID, username);
        log.trace("create session: {}", sessionDao.getNotCheck(SessionConstants.USER_ID));
    }
}

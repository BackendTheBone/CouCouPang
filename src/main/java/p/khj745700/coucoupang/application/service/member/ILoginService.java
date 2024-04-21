package p.khj745700.coucoupang.application.service.member;

import p.khj745700.coucoupang.application.config.constant.SessionConstants;
import p.khj745700.coucoupang.application.dto.request.member.LoginRequest;

public interface ILoginService {
    boolean login(LoginRequest loginRequest);

    public void loginCheck();
    void logout();
}

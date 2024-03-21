package p.khj745700.coucoupang.application.service.member;

import p.khj745700.coucoupang.application.dto.request.member.SignupRequest;

public interface ISignupMemberService {

    boolean signup(SignupRequest request);
}

package p.khj745700.coucoupang.application.service.member.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import p.khj745700.coucoupang.application.dao.MailCertificationDao;
import p.khj745700.coucoupang.application.dao.MemberDao;
import p.khj745700.coucoupang.application.dto.request.member.SignupRequest;
import p.khj745700.coucoupang.application.exception.DuplicateMemberException;
import p.khj745700.coucoupang.application.service.member.ISignupMemberService;
import p.khj745700.coucoupang.application.utils.PasswordEncrypt;

@Service
@RequiredArgsConstructor
public class SignupMemberService implements ISignupMemberService {
    private final MemberDao memberDao;
    private final PasswordEncrypt passwordEncrypt;
    private final MailCertificationDao mailCertificationDao;
    @Override
    public boolean signup(SignupRequest request) {
        if(validateSignup(request)){
            throw new DuplicateMemberException();
        }
        //TODO : 이메일 인증은 받았는지 처리 필요
        if(!mailCertificationDao.isCertificationEmail(request.getEmail(), request.getUuid())) {
            return false;
        }

        String encryptedPassword = passwordEncrypt.encode(request.getPassword());
        memberDao.save(request.toEntity(encryptedPassword));
        return true;
    }


    private boolean validateSignup(SignupRequest request) {
        return memberDao.existId(request.getUserId());
    }
}

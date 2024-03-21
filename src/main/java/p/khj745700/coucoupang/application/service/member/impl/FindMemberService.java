package p.khj745700.coucoupang.application.service.member.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import p.khj745700.coucoupang.application.dao.MemberDao;
import p.khj745700.coucoupang.application.domain.member.Member;
import p.khj745700.coucoupang.application.dto.request.member.LoginRequest;
import p.khj745700.coucoupang.application.service.member.IFindMemberService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FindMemberService implements IFindMemberService {
    private final MemberDao memberDao;

    @Override
    @Transactional(readOnly = true)
    public boolean existMemberId(String id) {
        return memberDao.existId(id);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean equalMemberPassword(LoginRequest request) {
        return memberDao.isVerificationUsernameAndPassword(request);
    }
}

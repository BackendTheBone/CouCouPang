package p.khj745700.coucoupang.application.service.member.impl;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import p.khj745700.coucoupang.application.config.constant.SessionConstants;
import p.khj745700.coucoupang.application.dao.MemberDao;
import p.khj745700.coucoupang.application.dao.SellerDao;
import p.khj745700.coucoupang.application.domain.member.Member;
import p.khj745700.coucoupang.application.service.member.IWithdrawMemberService;

@Service
@RequiredArgsConstructor
public class WithdrawMemberService implements IWithdrawMemberService {

    private final MemberDao memberDao;
    private final SellerDao sellerDao;
    private final HttpSession session;

    @Override
    public boolean withdrawMemberWithSessionCheck(String username) {
        if(!session.getAttribute(SessionConstants.USER_ID).equals(username)) {
            session.removeAttribute(SessionConstants.USER_ID);
            return false;
        }
        return withdrawMember(username);
    }

    @Override
    @Transactional
    public boolean withdrawMember(String username) {
        Member member = memberDao.getById(username);
        memberDao.delete(member);
        sellerDao.delete(username);
        //결제내역은 삭제하면 안됨. 결제 내역은 판매자의 상품 판매 이력이 되기때문.
        return true;
    }
}

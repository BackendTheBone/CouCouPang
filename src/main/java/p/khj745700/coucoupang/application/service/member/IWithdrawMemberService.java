package p.khj745700.coucoupang.application.service.member;

public interface IWithdrawMemberService {
    boolean withdrawMemberWithSessionCheck(String username);
    boolean withdrawMember(String username);
}

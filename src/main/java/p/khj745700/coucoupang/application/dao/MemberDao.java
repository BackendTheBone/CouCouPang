package p.khj745700.coucoupang.application.dao;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import p.khj745700.coucoupang.application.domain.member.Member;
import p.khj745700.coucoupang.application.domain.member.MemberRepository;
import p.khj745700.coucoupang.application.dto.request.member.LoginRequest;
import p.khj745700.coucoupang.application.exception.CustomException;
import p.khj745700.coucoupang.application.exception.DuplicateMemberException;
import p.khj745700.coucoupang.application.exception.NotFoundMemberException;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

@Component
@RequiredArgsConstructor
@Slf4j
public class MemberDao {
    private final MemberRepository memberRepository;

    public Member save(Member member) {
        return memberRepository.save(member);
    }

    public Member getById(String id) {
        return memberRepository.findById(id).orElseThrow(ExceptionHandler.NOT_FOUND.apply(id));
    }

    public Optional<Member> findById(String id) {
        return memberRepository.findById(id);
    }


    public boolean existId(String id) {
        return memberRepository.existsById(id);
    }

    public void ifExistIdThrowException(String id) {
        if(existId(id)) {
            throw ExceptionHandler.DUPLICATE.apply(id).get();
        }
    }

    public boolean isVerificationUsernameAndPassword(LoginRequest request) {
        Optional<Member> byId = findById(request.getUserId());
        if(byId.isEmpty()) {
            return false;
        }

        return byId.get().getPassword().equals(request.getPassword());
    }

    public void delete(Member member) {
        memberRepository.delete(member);
    }

    @Getter
    private static class ExceptionHandler {
        private static final Function<String, Supplier<CustomException>> NOT_FOUND;
        private static final Function<String, Supplier<CustomException>> DUPLICATE;
        static {
            NOT_FOUND = (String info) -> {
                log.trace("사용자를 찾을 수 없습니다. pk:{}", info);
                return NotFoundMemberException::new;
            };
            DUPLICATE = (String info) -> {
                log.trace("사용자가 이미 존재합니다. pk:{}", info);
                return DuplicateMemberException::new;
            };
        }
    }
}

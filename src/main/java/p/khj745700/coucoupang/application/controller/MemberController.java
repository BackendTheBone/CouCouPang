package p.khj745700.coucoupang.application.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import p.khj745700.coucoupang.application.dto.request.member.LoginRequest;
import p.khj745700.coucoupang.application.service.member.IFindMemberService;
import p.khj745700.coucoupang.application.service.member.IModifyMemberService;
import p.khj745700.coucoupang.application.service.member.ISignupMemberService;
import p.khj745700.coucoupang.application.service.member.IWithdrawMemberService;

import static p.khj745700.coucoupang.application.dto.response.ResponseConstants.*;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/member")
public class MemberController {
    private final IFindMemberService findMemberService;
    private final IWithdrawMemberService withdrawMemberService;


    @DeleteMapping("/{username}")
    public ResponseEntity<?> memberWithdraw(@PathVariable(name = "username") String username) {
        if(withdrawMemberService.withdrawMemberWithSessionCheck(username)) {
            return NO_CONTENT;
        }
        return UNAUTHORIZED;
    }
}

package p.khj745700.coucoupang.application.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import p.khj745700.coucoupang.application.dto.request.UUIDToken;
import p.khj745700.coucoupang.application.dto.request.member.EmailCertificateRequest;
import p.khj745700.coucoupang.application.dto.request.member.LoginRequest;
import p.khj745700.coucoupang.application.dto.request.member.SignupRequest;
import p.khj745700.coucoupang.application.service.mail.ICertificateMailService;
import p.khj745700.coucoupang.application.service.member.IFindMemberService;
import p.khj745700.coucoupang.application.service.member.ILoginService;
import p.khj745700.coucoupang.application.service.member.ISignupMemberService;

import static p.khj745700.coucoupang.application.dto.response.ResponseConstants.*;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/auth")
public class AuthController {
    private final ICertificateMailService certificateMailService;
    private final ILoginService loginService;
    private final IFindMemberService findMemberService;
    private final ISignupMemberService signupMemberService;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignupRequest request) {
        if(signupMemberService.signup(request)) {
            return ACCEPTED;
        }
        return CONFLICT;
    }

    @PostMapping("/signup/mail")
    public ResponseEntity<?> sendCertificateMail(@RequestBody EmailCertificateRequest request) {
        String generate = certificateMailService.sendEmail(request.getEmail());
        return ResponseEntity.ok(UUIDToken.builder().uuid(generate).build());
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        if (loginService.login(loginRequest)) {
            return ACCEPTED;
        }
        return UNAUTHORIZED;
    }

    @DeleteMapping("/logout")
    public ResponseEntity<?> logout() {
        loginService.logout();
        return ACCEPTED;
    }

    @GetMapping("/id/{username}")
    public ResponseEntity<?> existUsername(@PathVariable(value = "username") String username) {
        if(findMemberService.existMemberId(username)) {
            return FORBIDDEN;
        }
        return OK;
    }

    @PostMapping("/valid")
    public ResponseEntity<?> validUsernameAndPassword(LoginRequest request) {
        if(findMemberService.equalMemberPassword(request)) {
            return OK;
        }
        return FORBIDDEN;
    }
}

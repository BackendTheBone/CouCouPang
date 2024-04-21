package p.khj745700.coucoupang.application.aop;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import p.khj745700.coucoupang.application.service.member.ILoginService;


@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
public class LoginCheckAspect {

    private final ILoginService loginService;

    @Around("@annotation(p.khj745700.coucoupang.application.aop.annotation.LoginCheck)")
    public Object loginCheck(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        loginService.loginCheck();
        return proceedingJoinPoint.proceed();
    }
}

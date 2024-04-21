package p.khj745700.coucoupang.application.aop;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import p.khj745700.coucoupang.application.aop.annotation.PreAuth;
import p.khj745700.coucoupang.application.config.constant.SessionConstants;
import p.khj745700.coucoupang.application.dao.MemberDao;
import p.khj745700.coucoupang.application.dao.SellerDao;
import p.khj745700.coucoupang.application.dao.SessionDao;
import p.khj745700.coucoupang.application.domain.member.MemberType;
import p.khj745700.coucoupang.application.service.member.IFindMemberService;
import p.khj745700.coucoupang.application.service.member.ILoginService;

@Aspect
@RequiredArgsConstructor
@Slf4j
@Component
public class PreAuthCheckAspect {
    private final ILoginService iLoginService;
    private final SellerDao sellerDao;
    private final SessionDao sessionDao;
    @Around("@annotation(p.khj745700.coucoupang.application.aop.annotation.PreAuth)")
    public Object loginCheck(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
        PreAuth preAuth = methodSignature.getMethod().getAnnotation(PreAuth.class);

        iLoginService.loginCheck();

        if(isSeller(preAuth.value())) {
            sellerDao.findByUsernameIfNotExistThrowException(sessionDao.getNotCheck(SessionConstants.USER_ID));
        }

        return proceedingJoinPoint.proceed();
    }

    private boolean isSeller(MemberType memberType) {
        return memberType.equals(MemberType.SELLER);
    }
}

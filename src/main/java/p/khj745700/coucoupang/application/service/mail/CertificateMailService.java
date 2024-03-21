package p.khj745700.coucoupang.application.service.mail;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.redis.core.StringRedisTemplate;
import p.khj745700.coucoupang.application.config.constant.RedisConstant;
import p.khj745700.coucoupang.application.dao.MailCertificationDao;
import p.khj745700.coucoupang.application.dto.request.member.EmailCertificateRequest;
import p.khj745700.coucoupang.application.utils.CertificationGenerator;
import p.khj745700.coucoupang.application.utils.UUIDGenerator;
import p.khj745700.coucoupang.application.vo.MailCeritificationVO;

import java.util.Objects;

@RequiredArgsConstructor
public class CertificateMailService implements ICertificateMailService{
    private final StringRedisTemplate redisTemplate;
    private final UUIDGenerator uuidGenerator;
    private final ApplicationEventPublisher eventPublisher;
    private final CertificationGenerator certificationGenerator;
    private final MailCertificationDao mailCertificationDao;
    @Override
    public String sendEmail(String email) {
        String generateUuid = uuidGenerator.get();
        String certificationNum = certificationGenerator.createCertificationNumber();
        eventPublisher.publishEvent(
                MailCeritificationVO.builder()
                .toEmail(email)
                .certificationNumber(certificationNum)
                .build()
        );
        //TODO : 사용자가 반복적으로 5번 이상 반복 요청한 경우? 어떻게 처리할 것인지? 특정 사용자를 어떻게 특정할 수 있을지? IP로 한다?
        mailCertificationDao.saveCertificationNumber(
                EmailCertificateRequest.builder()
                        .certificationNum(certificationNum)
                        .email(email)
                        .uuid(generateUuid)
                        .build()
        );
        return generateUuid;
    }

    @Override
    public boolean certificateEmail(EmailCertificateRequest request) {
        return mailCertificationDao.certificateEmail(request);
    }

}

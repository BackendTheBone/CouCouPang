package p.khj745700.coucoupang.application.async.mail;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import p.khj745700.coucoupang.application.utils.CertificationMailSender;
import p.khj745700.coucoupang.application.vo.MailCeritificationVO;

@Component
@Async("Mail")
@RequiredArgsConstructor
@Slf4j
public class RequestOutAPISubscriber {

    private final CertificationMailSender certificationMailSender;

    @EventListener
    public void handleCertificationMailSendEvent(MailCeritificationVO vo) {
        certificationMailSender.sendEmail(vo.getToEmail(), vo.getCertificationNumber());
    }
}

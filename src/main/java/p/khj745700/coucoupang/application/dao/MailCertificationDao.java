package p.khj745700.coucoupang.application.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;
import p.khj745700.coucoupang.application.config.constant.RedisConstant;
import p.khj745700.coucoupang.application.dto.request.member.EmailCertificateRequest;

import java.util.Objects;

@Repository
@RequiredArgsConstructor
public class MailCertificationDao {
    private final StringRedisTemplate redisTemplate;
    private static final String EMAIL_POSTFIX = "-EMAIL";
    private static final String OK_POSTFIX = "-OK";

    public void saveCertificationNumber(EmailCertificateRequest request) {
        redisTemplate.opsForValue().set(request.getUuid(), request.getCertificationNum(), RedisConstant.EMAIL_CERTIFICATION_EXPIRATION.getSecond());
        redisTemplate.opsForValue().set(toEmailKey(request.getUuid()), request.getEmail(), RedisConstant.EMAIL_CERTIFICATION_EXPIRATION.getSecond());
    }

    public boolean certificateEmail(EmailCertificateRequest request) {
        String savedCertificationNum = getValue(toEmailKey(request.getUuid()));
        if(Objects.equals(savedCertificationNum, request.getCertificationNum())){
            redisTemplate.opsForValue().set(request.getEmail(), toOkValue(request.getUuid()), RedisConstant.EMAIL_VERIFICATION_EXPIRATION.getSecond());
            return true;
        }
        return false;
    }

    public boolean isCertificationEmail(String email, String uuid) {
        return isOk(email, uuid);
    }

    private String getValue(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    private boolean isOk(String email, String uuid) {
        return getValue(email).equals(uuid + OK_POSTFIX);
    }


    private String toOkValue(String uuid) {
        return uuid + OK_POSTFIX;
    }
    private String toEmailKey(String uuid) {
        return uuid + EMAIL_POSTFIX;
    }
}

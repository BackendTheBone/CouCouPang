package p.khj745700.coucoupang.application.service.mail;

import org.springframework.http.ResponseEntity;
import p.khj745700.coucoupang.application.dto.request.member.EmailCertificateRequest;

public interface ICertificateMailService {

    /**
     *
     * @param email;
     * @return UUID;
     */
    String sendEmail(String email);

    boolean certificateEmail(EmailCertificateRequest request);
}

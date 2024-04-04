package p.khj745700.coucoupang.application.utils;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Component
@RequiredArgsConstructor
public class CertificationMailSender {
    private final JavaMailSender emailSender;

    private final TemplateEngine templateEngine;

    @Value("${spring.mail.username}") private String username;

    public void sendEmail(String toEmail, String certificationNumber) {
        try {
            MimeMessage emailForm = createEmailForm(toEmail, certificationNumber);
            emailSender.send(emailForm);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }



    private MimeMessage createEmailForm(String toEmail, String certificationNumber) throws MessagingException {
        //인증 토큰 생성

        MimeMessage message = emailSender.createMimeMessage();
        message.addRecipients(Message.RecipientType.TO, toEmail);
        message.setSubject("[CC]인증번호 안내입니다.");
        message.setFrom(username+"@naver.com");
        message.setText(setContext(certificationNumber), "utf-8", "html");

        return message;
    }

    private String setContext(String certificationNumber){
        Context context = new Context();
        context.setVariable("certificationNumber", certificationNumber);
        return templateEngine.process("mail", context);
    }


}

package p.khj745700.coucoupang.application.config;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
@AllArgsConstructor
@NoArgsConstructor
public class MailConfig {


    @Value("${spring.mail.host}") private String host;
    @Value("${spring.mail.username}") private String username;
    @Value("${spring.mail.password}") private String password;
    @Value("${spring.mail.port}") private Integer port;
    @Value("${spring.mail.properties.mail.transport.protocol}") private String protocol;
    @Value("${spring.mail.properties.mail.smtp.auth}") private String isSmtpAuth;
    @Value("${spring.mail.properties.mail.smtp.starttls.enable}") private String isStarttls;
    @Value("${spring.mail.properties.mail.smtp.ssl.enable}") private String isSsl;
    @Value("${spring.mail.properties.mail.smtp.ssl.trust}") private String sslTrustServer;
    @Value("${spring.mail.properties.mail.debug}") private String isDebug;

    @Bean
    public JavaMailSender javaMailSender(){
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();

        javaMailSender.setHost(host);
        javaMailSender.setUsername(username);
        javaMailSender.setPassword(password);
        javaMailSender.setPort(port);

        javaMailSender.setJavaMailProperties(getMailProperties());

        return javaMailSender;
    }

    private Properties getMailProperties() {
        Properties properties = new Properties();
        properties.setProperty("mail.transport.protocol", protocol);
        properties.setProperty("mail.smtp.auth", isSmtpAuth);
        properties.setProperty("mail.smtp.starttls.enable", isStarttls);
        properties.setProperty("mail.debug", isDebug);
        properties.setProperty("mail.smtp.ssl.trust", sslTrustServer);
        properties.setProperty("mail.smtp.ssl.enable", isSsl);
        return properties;
    }
}

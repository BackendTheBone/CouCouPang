package p.khj745700.coucoupang.application.vo;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MailCeritificationVO {
    private String certificationNumber;
    private String toEmail;
}

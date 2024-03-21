package p.khj745700.coucoupang.application.dto.request.member;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmailCertificateRequest {
    private String email;
    private String uuid;
    private String certificationNum;
}

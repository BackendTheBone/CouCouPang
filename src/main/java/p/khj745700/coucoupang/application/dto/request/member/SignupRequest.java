package p.khj745700.coucoupang.application.dto.request.member;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import p.khj745700.coucoupang.application.domain.member.Member;
import p.khj745700.coucoupang.application.domain.member.MemberType;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class SignupRequest {
    @NotNull
    private String userId;

    @NotNull
    private String phoneNum;

    @NotNull
    private String password;
    @NotNull
    private String name;

    @NotNull
    private String email;

    @NotNull
    private String uuid;

    public Member toEntity(String encryptedPassword) {
        return Member.builder()
                .password(encryptedPassword)
                .name(name)
                .phoneNum(phoneNum)
                .username(userId)
                .email(email)
                .build();
    }
}

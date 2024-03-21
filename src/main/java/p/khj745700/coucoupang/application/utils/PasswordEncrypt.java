package p.khj745700.coucoupang.application.utils;


import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class  PasswordEncrypt extends BCryptPasswordEncoder {
}

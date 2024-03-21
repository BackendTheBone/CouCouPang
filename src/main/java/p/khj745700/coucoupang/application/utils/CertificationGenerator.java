package p.khj745700.coucoupang.application.utils;

import org.springframework.stereotype.Component;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

@Component
public class CertificationGenerator {
    public String createCertificationNumber() {
        String result;

        do {
            int num = 0;
            try {
                num = SecureRandom.getInstanceStrong().nextInt(999999);
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
            result = String.valueOf(num);
        }while (result.length() != 6);

        return result;
    }
}

package p.khj745700.coucoupang.application.config.constant;

import java.time.Duration;

public enum RedisConstant {
    EMAIL_CERTIFICATION_EXPIRATION(5),
    EMAIL_VERIFICATION_EXPIRATION(30);
    private final int minute;
    RedisConstant(int i) {
        minute = i;
    }
    public Duration getMinute() {
        return Duration.ofMinutes(minute);
    }

    public Duration getSecond() {
        return Duration.ofSeconds(minute * 60L);
    }
}

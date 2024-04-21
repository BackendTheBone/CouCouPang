package p.khj745700.coucoupang.application.config.constant;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

public enum SessionConstants {
    USER_ID("USER_ID");

    private final String value;

    public String getValue() {
        return value;
    }

    SessionConstants(String val) {
        value = val;
    }

}

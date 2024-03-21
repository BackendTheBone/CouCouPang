package p.khj745700.coucoupang.application.dto.response;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public final class ResponseConstants {
    public static final ResponseEntity<?> OK = ResponseEntity.ok(null);
    public static final ResponseEntity<?> ACCEPTED = ResponseEntity.accepted().body(null);
    public static final ResponseEntity<?> NO_CONTENT = ResponseEntity.noContent().build();
    public static final ResponseEntity<?> FORBIDDEN = ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
    public static final ResponseEntity<?> CONFLICT = ResponseEntity.status(HttpStatus.CONFLICT).body(null);
    public static final ResponseEntity<?> UNAUTHORIZED = ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

    private ResponseConstants() throws IllegalAccessException {
        throw new IllegalAccessException();
    }
}

package p.khj745700.coucoupang.application.domain.session;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Session {
    private String sessionId;
    private String username;
    private LocalDateTime createdDateTime;
}

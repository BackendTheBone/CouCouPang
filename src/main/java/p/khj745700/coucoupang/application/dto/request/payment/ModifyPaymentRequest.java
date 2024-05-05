package p.khj745700.coucoupang.application.dto.request.payment;

import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class ModifyPaymentRequest {
    Long payProductId;
    Integer count;
}

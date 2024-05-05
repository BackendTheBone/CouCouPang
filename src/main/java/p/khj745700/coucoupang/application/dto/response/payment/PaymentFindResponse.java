package p.khj745700.coucoupang.application.dto.response.payment;

import lombok.*;
import p.khj745700.coucoupang.application.domain.payment.product.PayProductStatus;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class PaymentFindResponse {
    private Long payProductId;
    private Long productId;
    private Integer count;
    private Integer price;
    private PayProductStatus status;
}

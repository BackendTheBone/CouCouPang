package p.khj745700.coucoupang.application.dto.response.order;

import lombok.*;
import p.khj745700.coucoupang.application.domain.order.item.OrderItemStatus;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class OrderFindResponse {
    private Long orderItemId;
    private Long productId;
    private Integer quantity;
    private Integer price;
    private OrderItemStatus status;
}

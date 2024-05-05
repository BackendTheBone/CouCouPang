package p.khj745700.coucoupang.application.service.order;

import p.khj745700.coucoupang.application.dto.response.order.OrderFindResponse;

import java.util.List;

public interface IFindOrderService {
    List<OrderFindResponse> find(Long orderId);
}

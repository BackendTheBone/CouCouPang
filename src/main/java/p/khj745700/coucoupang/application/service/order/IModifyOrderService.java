package p.khj745700.coucoupang.application.service.order;

import p.khj745700.coucoupang.application.dto.request.order.OrderModifyRequest;

import java.util.List;

public interface IModifyOrderService {
    void modify(List<OrderModifyRequest> requests);
}

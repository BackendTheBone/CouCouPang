package p.khj745700.coucoupang.application.service.order;

import p.khj745700.coucoupang.application.dto.request.order.OrderRegisterRequest;

import java.util.List;

public interface IRegisterOrderService {
    Long register(List<OrderRegisterRequest> requests);
}

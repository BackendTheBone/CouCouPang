package p.khj745700.coucoupang.application.service.order.item;

import p.khj745700.coucoupang.application.domain.order.Order;
import p.khj745700.coucoupang.application.domain.order.item.OrderItem;
import p.khj745700.coucoupang.application.dto.request.order.OrderRegisterRequest;

public interface IRegisterOrderItemService {
    OrderItem register(OrderRegisterRequest request, Order order);
}

package p.khj745700.coucoupang.application.service.order.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import p.khj745700.coucoupang.application.dao.OrderDao;
import p.khj745700.coucoupang.application.dao.OrderItemDao;
import p.khj745700.coucoupang.application.domain.order.Order;
import p.khj745700.coucoupang.application.domain.order.item.OrderItem;
import p.khj745700.coucoupang.application.dto.response.order.OrderFindResponse;
import p.khj745700.coucoupang.application.service.order.IFindOrderService;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FindOrderService implements IFindOrderService {

    OrderDao orderDao;
    OrderItemDao orderItemDao;

    @Override
    public List<OrderFindResponse> find(Long orderId) {

        Order order = orderDao.findByIdIfNotExistThrowException(orderId);
        List<OrderItem> orderItems = orderItemDao.findByOrder(order);

        return orderItems.stream().map(orderItem -> OrderFindResponse.builder()
                .orderItemId(orderItem.getId())
                .productId(orderItem.getProduct().getId())
                .quantity(orderItem.getQuantity())
                .price(orderItem.getPrice())
                .status(orderItem.getStatus())
                .build()).toList();
    }

}

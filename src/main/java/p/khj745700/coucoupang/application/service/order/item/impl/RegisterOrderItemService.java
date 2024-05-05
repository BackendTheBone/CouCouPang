package p.khj745700.coucoupang.application.service.order.item.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import p.khj745700.coucoupang.application.dao.OrderItemDao;
import p.khj745700.coucoupang.application.dao.ProductDao;
import p.khj745700.coucoupang.application.domain.order.Order;
import p.khj745700.coucoupang.application.domain.order.item.OrderItem;
import p.khj745700.coucoupang.application.domain.product.Product;
import p.khj745700.coucoupang.application.dto.request.order.OrderRegisterRequest;
import p.khj745700.coucoupang.application.service.order.item.IRegisterOrderItemService;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RegisterOrderItemService implements IRegisterOrderItemService {

    OrderItemDao orderItemDao;
    ProductDao productDao;

    @Override
    public OrderItem register(OrderRegisterRequest request, Order order) {

        Product product = productDao.findByIdIfNotExistThrowException(request.getProductId());

        OrderItem orderItem = OrderItem.builder()
                .order(order)
                .product(product)
                .quantity(request.getQuantity())
                .build();

        orderItemDao.save(orderItem);

        return orderItem;
    }

}

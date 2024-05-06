package p.khj745700.coucoupang.application.service.order.item.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import p.khj745700.coucoupang.application.dao.OrderItemDao;
import p.khj745700.coucoupang.application.domain.order.item.OrderItem;
import p.khj745700.coucoupang.application.domain.order.item.OrderItemStatus;
import p.khj745700.coucoupang.application.dto.request.order.OrderModifyRequest;
import p.khj745700.coucoupang.application.exception.OrderItemAlreadyShippedException;
import p.khj745700.coucoupang.application.service.order.item.IModifyOrderItemService;

@Service
@Transactional
@RequiredArgsConstructor
public class ModifyOrderItemService implements IModifyOrderItemService {

    OrderItemDao orderItemDao;

    @Override
    public void modify(OrderModifyRequest request) {

        OrderItem orderItem = orderItemDao.findByIdIfNotExistThrowException(request.getOrderItemId());

        validateAlreadyShipped(orderItem);

        int difference = request.getQuantity() - orderItem.getQuantity();
        if (difference > 0) {
            int maximumAllowableQuantity = orderItem.getProduct().removeStock(difference);
            orderItem.addQuantity(maximumAllowableQuantity);
        } else {
            orderItem.getProduct().addStock(difference);
            orderItem.removeQuantity(difference);
        }
    }

    private void validateAlreadyShipped(OrderItem orderItem) {
        if (orderItem.getStatus().compareTo(OrderItemStatus.PREPARING) > 0) {
            throw new OrderItemAlreadyShippedException();
        }
    }

}

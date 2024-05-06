package p.khj745700.coucoupang.application.service.order.item.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import p.khj745700.coucoupang.application.dao.OrderItemDao;
import p.khj745700.coucoupang.application.domain.order.item.OrderItem;
import p.khj745700.coucoupang.application.domain.order.item.OrderItemStatus;
import p.khj745700.coucoupang.application.exception.OrderItemAlreadyShippedException;
import p.khj745700.coucoupang.application.service.order.item.IRemoveOrderItemService;

@Service
@Transactional
@RequiredArgsConstructor
public class RemoveOrderItemService implements IRemoveOrderItemService {

    OrderItemDao orderItemDao;

    @Override
    public void remove(Long orderItemId) {
        OrderItem orderItem = orderItemDao.findByIdIfNotExistThrowException(orderItemId);
        validateAlreadyShipped(orderItem);
        orderItem.canceled();
    }

    private void validateAlreadyShipped(OrderItem orderItem) {
        if (orderItem.getStatus().compareTo(OrderItemStatus.PREPARING) > 0) {
            throw new OrderItemAlreadyShippedException();
        }
    }

}

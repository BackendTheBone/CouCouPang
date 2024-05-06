package p.khj745700.coucoupang.application.service.order.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import p.khj745700.coucoupang.application.service.order.IRemoveOrderService;
import p.khj745700.coucoupang.application.service.order.item.IRemoveOrderItemService;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class RemoveOrderService implements IRemoveOrderService {

    IRemoveOrderItemService removeOrderItemService;

    @Override
    public void remove(List<Long> orderItemIds) {
        for (Long orderItemId : orderItemIds) {
            removeOrderItemService.remove(orderItemId);
        }
    }

}

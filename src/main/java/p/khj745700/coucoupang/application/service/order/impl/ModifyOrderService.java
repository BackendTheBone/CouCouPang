package p.khj745700.coucoupang.application.service.order.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import p.khj745700.coucoupang.application.dto.request.order.OrderModifyRequest;
import p.khj745700.coucoupang.application.service.order.IModifyOrderService;
import p.khj745700.coucoupang.application.service.order.item.IModifyOrderItemService;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ModifyOrderService implements IModifyOrderService {

    IModifyOrderItemService modifyOrderItemService;

    @Override
    public void modify(List<OrderModifyRequest> requests) {
        for (OrderModifyRequest request : requests) {
            modifyOrderItemService.modify(request);
        }
    }

}

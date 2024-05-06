package p.khj745700.coucoupang.application.service.order.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import p.khj745700.coucoupang.application.config.constant.SessionConstants;
import p.khj745700.coucoupang.application.dao.MemberDao;
import p.khj745700.coucoupang.application.dao.OrderDao;
import p.khj745700.coucoupang.application.dao.SessionDao;
import p.khj745700.coucoupang.application.domain.member.Member;
import p.khj745700.coucoupang.application.domain.order.Order;
import p.khj745700.coucoupang.application.domain.order.item.OrderItem;
import p.khj745700.coucoupang.application.dto.request.order.OrderRegisterRequest;
import p.khj745700.coucoupang.application.exception.PaymentFailedException;
import p.khj745700.coucoupang.application.service.order.IRegisterOrderService;
import p.khj745700.coucoupang.application.service.order.item.IRegisterOrderItemService;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class RegisterOrderService implements IRegisterOrderService {

    OrderDao orderDao;
    SessionDao sessionDao;
    MemberDao memberDao;
    IRegisterOrderItemService registerOrderItemService;

    @Override
    public Long register(List<OrderRegisterRequest> requests) {

        String username = sessionDao.getNotCheck(SessionConstants.USER_ID);
        Member member = memberDao.getById(username);

        Order order = Order.builder()
                .member(member)
                .build();

        List<OrderItem> orderItems = new ArrayList<>();
        for (OrderRegisterRequest request : requests) {
            OrderItem orderItem = registerOrderItemService.register(request, order);
            orderItems.add(orderItem);
        }

        orderDao.save(order);

        try {
            validatePaymentFailed();
            for (OrderItem orderItem : orderItems) {
                orderItem.preparing();
            }
        } catch (PaymentFailedException e) {
            for (OrderItem orderItem : orderItems) {
                orderItem.canceled();
            }
        }

        return order.getId();
    }

    private void validatePaymentFailed() {
        if (Math.random() < 0.01) {
            throw new PaymentFailedException();
        }
    }

}

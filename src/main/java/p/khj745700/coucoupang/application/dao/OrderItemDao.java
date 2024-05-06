package p.khj745700.coucoupang.application.dao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import p.khj745700.coucoupang.application.domain.order.Order;
import p.khj745700.coucoupang.application.domain.order.item.OrderItem;
import p.khj745700.coucoupang.application.domain.order.item.OrderItemRepository;
import p.khj745700.coucoupang.application.exception.CustomException;
import p.khj745700.coucoupang.application.exception.OrderItemNotFoundException;

import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderItemDao {

    OrderItemRepository orderItemRepository;

    public OrderItem save(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }

    public OrderItem findByIdIfNotExistThrowException(Long orderItemId) {
        return orderItemRepository.findById(orderItemId).orElseThrow(ExceptionHandler.NOT_FOUND.apply(orderItemId));
    }

    public List<OrderItem> findByOrder(Order order) {
        return orderItemRepository.findByOrder(order);
    }

    private static class ExceptionHandler {

        private static final Function<Long, Supplier<CustomException>> NOT_FOUND;

        static {
            NOT_FOUND = (Long info) -> {
                log.trace("주문상품을 찾을 수 없습니다. pk:{}", info);
                return OrderItemNotFoundException::new;
            };
        }

    }

}

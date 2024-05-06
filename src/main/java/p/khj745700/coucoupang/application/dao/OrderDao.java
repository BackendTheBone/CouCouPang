package p.khj745700.coucoupang.application.dao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import p.khj745700.coucoupang.application.domain.order.Order;
import p.khj745700.coucoupang.application.domain.order.OrderRepository;
import p.khj745700.coucoupang.application.exception.CustomException;
import p.khj745700.coucoupang.application.exception.OrderNotFoundException;

import java.util.function.Function;
import java.util.function.Supplier;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderDao {

    OrderRepository orderRepository;

    public Order save(Order order) {
        return orderRepository.save(order);
    }

    public Order findByIdIfNotExistThrowException(Long orderId) {
        return orderRepository.findById(orderId).orElseThrow(ExceptionHandler.NOT_FOUND.apply(orderId));
    }

    private static class ExceptionHandler {

        private static final Function<Long, Supplier<CustomException>> NOT_FOUND;

        static {
            NOT_FOUND = (Long info) -> {
                log.trace("주문을 찾을 수 없습니다. pk:{}", info);
                return OrderNotFoundException::new;
            };
        }

    }

}

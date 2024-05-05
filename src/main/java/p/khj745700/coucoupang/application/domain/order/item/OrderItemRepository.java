package p.khj745700.coucoupang.application.domain.order.item;

import org.springframework.data.jpa.repository.JpaRepository;
import p.khj745700.coucoupang.application.domain.order.Order;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    List<OrderItem> findByOrder(Order order);
}

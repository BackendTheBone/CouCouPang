package p.khj745700.coucoupang.application.domain.payment.product;

import org.springframework.data.jpa.repository.JpaRepository;
import p.khj745700.coucoupang.application.domain.payment.Payment;

import java.util.List;

public interface PayProductRepository extends JpaRepository<PayProduct, Long> {
    List<PayProduct> findByPayment(Payment payment);
}

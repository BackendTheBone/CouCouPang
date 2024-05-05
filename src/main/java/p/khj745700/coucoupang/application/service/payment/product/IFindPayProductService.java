package p.khj745700.coucoupang.application.service.payment.product;

import p.khj745700.coucoupang.application.domain.payment.Payment;
import p.khj745700.coucoupang.application.domain.payment.product.PayProduct;

import java.util.List;

public interface IFindPayProductService {
    List<PayProduct> findByPayment(Payment payment);
}

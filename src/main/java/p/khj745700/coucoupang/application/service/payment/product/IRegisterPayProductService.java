package p.khj745700.coucoupang.application.service.payment.product;

import p.khj745700.coucoupang.application.domain.payment.Payment;
import p.khj745700.coucoupang.application.domain.payment.product.PayProduct;
import p.khj745700.coucoupang.application.domain.product.ProductState;
import p.khj745700.coucoupang.application.dto.request.payment.RegisterPaymentRequest;

public interface IRegisterPayProductService {
    PayProduct register(RegisterPaymentRequest request, Payment payment);

    void validateProductState(Long productId, ProductState state);

    Integer getMaximumAllowableCount(Long productId, Integer count, Integer stock);
}

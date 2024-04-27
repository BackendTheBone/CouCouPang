package p.khj745700.coucoupang.application.service.payment.product.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import p.khj745700.coucoupang.application.dao.PayProductDao;
import p.khj745700.coucoupang.application.dao.ProductDao;
import p.khj745700.coucoupang.application.domain.payment.Payment;
import p.khj745700.coucoupang.application.domain.payment.product.PayProduct;
import p.khj745700.coucoupang.application.domain.product.Product;
import p.khj745700.coucoupang.application.domain.product.ProductState;
import p.khj745700.coucoupang.application.dto.request.payment.RegisterPaymentRequest;
import p.khj745700.coucoupang.application.service.payment.product.IRegisterPayProductService;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RegisterPayProductService implements IRegisterPayProductService {

    PayProductDao payProductDao;
    ProductDao productDao;

    @Override
    @Transactional
    public PayProduct register(RegisterPaymentRequest request, Payment payment) {

        // 상품 엔티티 조회
        Product product = productDao.findById(request.getProductId());

        // 상품 판매상태 검증
        validateProductState(product.getId(), product.getState());

        // 상품 구매가능수량 반환
        Integer maximumAllowableCount = getMaximumAllowableCount(product.getId(), request.getCount(), product.getStock());

        // 결제상품 생성
        PayProduct payProduct = PayProduct.builder()
                .payment(payment)
                .product(product)
                .count(maximumAllowableCount)
                .build();

        // 결제상품 저장
        payProductDao.save(payProduct);

        return payProduct;
    }

    @Override
    public void validateProductState(Long productId, ProductState state) {
        payProductDao.validateProductState(productId, state);
    }

    @Override
    public Integer getMaximumAllowableCount(Long productId, Integer count, Integer stock) {
        return payProductDao.getMaximumAllowableCount(productId, count, stock);
    }

}

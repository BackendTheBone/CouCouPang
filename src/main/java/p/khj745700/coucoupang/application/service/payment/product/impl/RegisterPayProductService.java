package p.khj745700.coucoupang.application.service.payment.product.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import p.khj745700.coucoupang.application.dao.PayProductDao;
import p.khj745700.coucoupang.application.dao.ProductDao;
import p.khj745700.coucoupang.application.domain.payment.Payment;
import p.khj745700.coucoupang.application.domain.payment.product.PayProduct;
import p.khj745700.coucoupang.application.domain.product.Product;
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
        Product product = productDao.findByIdIfNotExistThrowException(request.getProductId());

        // 결제상품 생성
        PayProduct payProduct = PayProduct.builder()
                .payment(payment)
                .product(product)
                .count(request.getCount())
                .build();

        // 결제상품 저장
        payProductDao.save(payProduct);

        return payProduct;
    }
}

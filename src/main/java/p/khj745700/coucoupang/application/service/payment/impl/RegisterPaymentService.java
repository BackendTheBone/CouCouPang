package p.khj745700.coucoupang.application.service.payment.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import p.khj745700.coucoupang.application.dao.PaymentDao;
import p.khj745700.coucoupang.application.domain.member.Member;
import p.khj745700.coucoupang.application.domain.payment.Payment;
import p.khj745700.coucoupang.application.domain.payment.product.PayProduct;
import p.khj745700.coucoupang.application.dto.request.payment.RegisterPaymentRequest;
import p.khj745700.coucoupang.application.exception.PaymentFailedException;
import p.khj745700.coucoupang.application.service.payment.IRegisterPaymentService;
import p.khj745700.coucoupang.application.service.payment.product.IRegisterPayProductService;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RegisterPaymentService implements IRegisterPaymentService {

    PaymentDao paymentDao;
    IRegisterPayProductService registerPayProductService;

    @Override
    @Transactional
    public Long register(List<RegisterPaymentRequest> requests, Member member) {

        // 결제상품 리스트 생성
        List<PayProduct> payProducts = new ArrayList<>();

        // 결제 생성
        Payment payment = Payment.builder()
                .member(member)
                .build();

        // 결제상품 생성 서비스 호출
        for (RegisterPaymentRequest request : requests) {
            PayProduct payProduct = registerPayProductService.register(request, payment);
            payProducts.add(payProduct);
        }

        // 결제 저장
        paymentDao.save(payment);

        // 결제 처리
        try {
            paymentDao.processPayment(payment);
            for (PayProduct payProduct : payProducts) {
                payProduct.processPayment();
            }
        } catch (PaymentFailedException e) {
            for (PayProduct payProduct : payProducts) {
                payProduct.cancel();
            }
        }

        return payment.getId();
    }

}

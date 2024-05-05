package p.khj745700.coucoupang.application.service.payment.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import p.khj745700.coucoupang.application.config.constant.SessionConstants;
import p.khj745700.coucoupang.application.dao.MemberDao;
import p.khj745700.coucoupang.application.dao.PaymentDao;
import p.khj745700.coucoupang.application.dao.SessionDao;
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
@Transactional
@RequiredArgsConstructor
public class RegisterPaymentService implements IRegisterPaymentService {

    PaymentDao paymentDao;
    SessionDao sessionDao;
    MemberDao memberDao;
    IRegisterPayProductService registerPayProductService;

    @Override
    public Long register(List<RegisterPaymentRequest> requests) {

        // 결제상품 리스트 생성
        List<PayProduct> payProducts = new ArrayList<>();

        // 사용자 조회
        String userName = sessionDao.getNotCheck(SessionConstants.USER_ID);
        Member member = memberDao.getById(userName);

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
                payProduct.preparing();
            }
        } catch (PaymentFailedException e) {
            for (PayProduct payProduct : payProducts) {
                payProduct.canceled();
            }
        }

        return payment.getId();
    }

}

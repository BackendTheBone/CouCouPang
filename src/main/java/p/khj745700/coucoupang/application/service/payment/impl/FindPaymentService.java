package p.khj745700.coucoupang.application.service.payment.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import p.khj745700.coucoupang.application.dao.PaymentDao;
import p.khj745700.coucoupang.application.domain.payment.Payment;
import p.khj745700.coucoupang.application.domain.payment.product.PayProduct;
import p.khj745700.coucoupang.application.dto.response.payment.PaymentFindResponse;
import p.khj745700.coucoupang.application.service.payment.IFindPaymentService;
import p.khj745700.coucoupang.application.service.payment.product.IFindPayProductService;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FindPaymentService implements IFindPaymentService {

    PaymentDao paymentDao;
    IFindPayProductService findPayProductService;

    @Override
    public List<PaymentFindResponse> find(Long id) {

        Payment payment = paymentDao.findByIdIfNotExistThrowException(id);

        List<PayProduct> payProducts = findPayProductService.findByPayment(payment);

        List<PaymentFindResponse> responses = new ArrayList<>();

        for (PayProduct payProduct : payProducts) {
            PaymentFindResponse response = PaymentFindResponse.builder()
                    .payProductId(payProduct.getId())
                    .productId(payProduct.getProduct().getId())
                    .count(payProduct.getCount())
                    .price(payProduct.getPrice())
                    .status(payProduct.getStatus())
                    .build();
            responses.add(response);
        }

        return responses;
    }

}

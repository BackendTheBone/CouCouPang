package p.khj745700.coucoupang.application.service.payment;

import p.khj745700.coucoupang.application.dto.response.payment.PaymentFindResponse;

import java.util.List;

public interface IFindPaymentService {
    List<PaymentFindResponse> find(Long paymentId);
}

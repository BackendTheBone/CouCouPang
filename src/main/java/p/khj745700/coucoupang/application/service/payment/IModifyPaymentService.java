package p.khj745700.coucoupang.application.service.payment;

import p.khj745700.coucoupang.application.dto.request.payment.ModifyPaymentRequest;

import java.util.List;

public interface IModifyPaymentService {
    void modify(List<ModifyPaymentRequest> requests);
}

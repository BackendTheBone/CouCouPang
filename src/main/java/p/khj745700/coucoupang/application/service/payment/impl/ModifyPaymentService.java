package p.khj745700.coucoupang.application.service.payment.impl;

import org.springframework.transaction.annotation.Transactional;
import p.khj745700.coucoupang.application.dto.request.payment.ModifyPaymentRequest;
import p.khj745700.coucoupang.application.service.payment.IModifyPaymentService;
import p.khj745700.coucoupang.application.service.payment.product.IModifyPayProductService;

import java.util.List;

public class ModifyPaymentService implements IModifyPaymentService {

    IModifyPayProductService modifyPayProductService;

    @Override
    @Transactional
    public void modify(List<ModifyPaymentRequest> requests) {
        for (ModifyPaymentRequest request : requests) {
            modifyPayProductService.modify(request);
        }
    }
}

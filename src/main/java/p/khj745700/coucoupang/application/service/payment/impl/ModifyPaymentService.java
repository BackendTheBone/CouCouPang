package p.khj745700.coucoupang.application.service.payment.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import p.khj745700.coucoupang.application.dto.request.payment.ModifyPaymentRequest;
import p.khj745700.coucoupang.application.service.payment.IModifyPaymentService;
import p.khj745700.coucoupang.application.service.payment.product.IModifyPayProductService;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ModifyPaymentService implements IModifyPaymentService {

    IModifyPayProductService modifyPayProductService;

    @Override
    public void modify(List<ModifyPaymentRequest> requests) {
        for (ModifyPaymentRequest request : requests) {
            modifyPayProductService.modify(request);
        }
    }

}

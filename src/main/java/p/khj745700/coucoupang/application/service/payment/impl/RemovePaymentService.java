package p.khj745700.coucoupang.application.service.payment.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import p.khj745700.coucoupang.application.service.payment.IRemovePaymentService;
import p.khj745700.coucoupang.application.service.payment.product.IRemovePayProductService;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class RemovePaymentService implements IRemovePaymentService {

    IRemovePayProductService removePayProductService;

    @Override
    public void remove(List<Long> payProductId) {
        for (Long id : payProductId) {
            removePayProductService.remove(id);
        }
    }

}

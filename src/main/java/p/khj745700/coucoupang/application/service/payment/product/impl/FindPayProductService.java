package p.khj745700.coucoupang.application.service.payment.product.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import p.khj745700.coucoupang.application.dao.PayProductDao;
import p.khj745700.coucoupang.application.domain.payment.Payment;
import p.khj745700.coucoupang.application.domain.payment.product.PayProduct;
import p.khj745700.coucoupang.application.service.payment.product.IFindPayProductService;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FindPayProductService implements IFindPayProductService {

    PayProductDao payProductDao;

    @Override
    public List<PayProduct> findByPayment(Payment payment) {
        return payProductDao.findByPayment(payment);
    }

}

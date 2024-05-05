package p.khj745700.coucoupang.application.service.payment.product.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import p.khj745700.coucoupang.application.dao.PayProductDao;
import p.khj745700.coucoupang.application.domain.payment.product.PayProduct;
import p.khj745700.coucoupang.application.domain.payment.product.PayProductStatus;
import p.khj745700.coucoupang.application.exception.PayProductAlreadyShippedException;
import p.khj745700.coucoupang.application.service.payment.product.IRemovePayProductService;

@Service
@Transactional
@RequiredArgsConstructor
public class RemovePayProductService implements IRemovePayProductService {

    PayProductDao payProductDao;

    @Override
    public void remove(Long id) {

        PayProduct payProduct = payProductDao.findByIdIfNotExistThrowException(id);

        validateAlreadyShipped(payProduct);

        payProduct.canceled();
    }

    private void validateAlreadyShipped(PayProduct payProduct) {
        if (payProduct.getStatus().compareTo(PayProductStatus.PREPARING) > 0) {
            throw new PayProductAlreadyShippedException();
        }
    }

}

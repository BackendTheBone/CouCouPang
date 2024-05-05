package p.khj745700.coucoupang.application.service.payment.product.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import p.khj745700.coucoupang.application.dao.PayProductDao;
import p.khj745700.coucoupang.application.domain.payment.product.PayProduct;
import p.khj745700.coucoupang.application.domain.payment.product.PayProductStatus;
import p.khj745700.coucoupang.application.dto.request.payment.ModifyPaymentRequest;
import p.khj745700.coucoupang.application.exception.PayProductAlreadyShippedException;
import p.khj745700.coucoupang.application.service.payment.product.IModifyPayProductService;

@Service
@Transactional
@RequiredArgsConstructor
public class ModifyPayProductService implements IModifyPayProductService {

    PayProductDao payProductDao;

    @Override
    public void modify(ModifyPaymentRequest request) {

        PayProduct payProduct = payProductDao.findByIdIfNotExistThrowException(request.getPayProductId());

        validateAlreadyShipped(payProduct);

        if (request.getCount() > payProduct.getCount()) {
            payProduct.getProduct().addStock(request.getCount() - payProduct.getCount());
        } else {
            payProduct.getProduct().removeStock(payProduct.getCount() - request.getCount());
        }
    }

    private void validateAlreadyShipped(PayProduct payProduct) {
        if (payProduct.getStatus().compareTo(PayProductStatus.PREPARING) > 0) {
            throw new PayProductAlreadyShippedException();
        }
    }

}

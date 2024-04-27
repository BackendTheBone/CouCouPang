package p.khj745700.coucoupang.application.dao;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import p.khj745700.coucoupang.application.domain.payment.product.PayProduct;
import p.khj745700.coucoupang.application.domain.payment.product.PayProductRepository;
import p.khj745700.coucoupang.application.domain.product.ProductState;
import p.khj745700.coucoupang.application.exception.CustomException;
import p.khj745700.coucoupang.application.exception.ProductOutOfStockException;
import p.khj745700.coucoupang.application.exception.ProductUnavailableException;

import java.util.function.Function;
import java.util.function.Supplier;

@Slf4j
@Component
@RequiredArgsConstructor
public class PayProductDao {

    PayProductRepository payProductRepository;

    public PayProduct save(PayProduct payProduct) {
        return payProductRepository.save(payProduct);
    }

    public void validateProductState(Long productId, ProductState state) {
        if (state != ProductState.SELLING) {
            throw ExceptionHandler.PRODUCT_UNAVAILABLE.apply(productId).get();
        }
    }

    public Integer getMaximumAllowableCount(Long productId, Integer count, Integer stock) {
        if (stock == 0) {
            throw ExceptionHandler.PRODUCT_OUT_OF_STOCK.apply(productId).get();
        }
        return Math.min(count, stock);
    }

    @Getter
    private static class ExceptionHandler {

        private static final Function<Long, Supplier<CustomException>> PRODUCT_UNAVAILABLE;
        private static final Function<Long, Supplier<CustomException>> PRODUCT_OUT_OF_STOCK;

        static {
            PRODUCT_UNAVAILABLE = (Long info) -> {
                log.trace("상품이 구매 불가합니다. pk:{}", info);
                return ProductUnavailableException::new;
            };
            PRODUCT_OUT_OF_STOCK = (Long info) -> {
                log.trace("상품의 재고가 부족합니다. pk:{}", info);
                return ProductOutOfStockException::new;
            };
        }

    }

}

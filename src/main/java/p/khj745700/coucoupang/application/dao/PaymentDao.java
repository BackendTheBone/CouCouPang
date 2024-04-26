package p.khj745700.coucoupang.application.dao;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import p.khj745700.coucoupang.application.domain.payment.Payment;
import p.khj745700.coucoupang.application.domain.payment.PaymentRepository;
import p.khj745700.coucoupang.application.exception.CustomException;
import p.khj745700.coucoupang.application.exception.PaymentFailedException;
import p.khj745700.coucoupang.application.exception.ProductOutOfStockException;
import p.khj745700.coucoupang.application.exception.ProductUnavailableException;

import java.util.function.Function;
import java.util.function.Supplier;

@Slf4j
@Component
@RequiredArgsConstructor
public class PaymentDao {

    private final PaymentRepository paymentRepository;

    public Payment save(Payment payment) {
        return paymentRepository.save(payment);
    }

    @Getter
    private static class ExceptionHandler {

        private static final Function<String, Supplier<CustomException>> PRODUCT_OUT_OF_STOCK;
        private static final Function<String, Supplier<CustomException>> PRODUCT_UNAVAILABLE;
        private static final Function<String, Supplier<CustomException>> PAYMENT_FAILED;

        static {
            PRODUCT_OUT_OF_STOCK = (String info) -> {
                log.trace("상품의 재고가 부족합니다. pk:{}", info);
                return ProductOutOfStockException::new;
            };
            PRODUCT_UNAVAILABLE = (String info) -> {
                log.trace("구매 불가한 상품입니다. pk:{}", info);
                return ProductUnavailableException::new;
            };
            PAYMENT_FAILED = (String info) -> {
                log.trace("결제에 실패하였습니다. pk:{}", info);
                return PaymentFailedException::new;
            };
        }

    }

}

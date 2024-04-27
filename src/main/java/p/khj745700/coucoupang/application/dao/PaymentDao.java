package p.khj745700.coucoupang.application.dao;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import p.khj745700.coucoupang.application.domain.payment.Payment;
import p.khj745700.coucoupang.application.domain.payment.PaymentRepository;
import p.khj745700.coucoupang.application.exception.CustomException;
import p.khj745700.coucoupang.application.exception.PaymentFailedException;

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

    public void processPayment(Payment payment) {
        if (Math.random() < 0.01) {
            throw ExceptionHandler.PAYMENT_FAILED.apply(payment.getId()).get();
        }
    }

    @Getter
    private static class ExceptionHandler {

        private static final Function<Long, Supplier<CustomException>> PAYMENT_FAILED;

        static {
            PAYMENT_FAILED = (Long info) -> {
                log.trace("결제에 실패하였습니다. pk:{}", info);
                return PaymentFailedException::new;
            };
        }

    }

}

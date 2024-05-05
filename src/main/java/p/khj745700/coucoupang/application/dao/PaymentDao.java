package p.khj745700.coucoupang.application.dao;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import p.khj745700.coucoupang.application.domain.payment.Payment;
import p.khj745700.coucoupang.application.domain.payment.PaymentRepository;
import p.khj745700.coucoupang.application.exception.CustomException;
import p.khj745700.coucoupang.application.exception.PaymentFailedException;
import p.khj745700.coucoupang.application.exception.PaymentNotFoundException;

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

    public Payment findByIdIfNotExistThrowException(Long id) {
        return paymentRepository.findById(id).orElseThrow(ExceptionHandler.NOT_FOUND.apply(id));
    }

    public void processPayment(Payment payment) {
        if (Math.random() < 0.01) {
            throw ExceptionHandler.PAYMENT_FAILED.apply(payment.getId()).get();
        }
    }

    @Getter
    private static class ExceptionHandler {

        private static final Function<Long, Supplier<CustomException>> PAYMENT_FAILED;
        private static final Function<Long, Supplier<CustomException>> NOT_FOUND;

        static {
            PAYMENT_FAILED = (Long info) -> {
                log.trace("결제에 실패하였습니다. pk:{}", info);
                return PaymentFailedException::new;
            };
            NOT_FOUND = (Long info) -> {
                log.trace("주문을 찾을 수 없습니다. pk:{}", info);
                return PaymentNotFoundException::new;
            };
        }

    }

}

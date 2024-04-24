package p.khj745700.coucoupang.application.dao;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import p.khj745700.coucoupang.application.domain.product.description.ProductDescription;
import p.khj745700.coucoupang.application.domain.product.description.ProductDescriptionRepository;
import p.khj745700.coucoupang.application.exception.CustomException;
import p.khj745700.coucoupang.application.exception.DuplicateMemberException;
import p.khj745700.coucoupang.application.exception.NotFoundMemberException;
import p.khj745700.coucoupang.application.exception.NotFoundProductDescriptionException;

import java.util.function.Function;
import java.util.function.Supplier;

@Component
@RequiredArgsConstructor
@Slf4j
public class ProductDescriptionDao {
    private final ProductDescriptionRepository productDescriptionRepository;

    public ProductDescription save(ProductDescription newProductDescription) {
        return productDescriptionRepository.save(newProductDescription);
    }

    public ProductDescription findByProductIdIfNotExistThrowException(Long productId) {
        return productDescriptionRepository.findProductDescriptionByProduct_Id(productId).orElseThrow(ExceptionHandler.NOT_FOUND.apply(productId));
    }

    @Getter
    private static class ExceptionHandler {
        private static final Function<Long, Supplier<CustomException>> NOT_FOUND;
        static {
            NOT_FOUND = (Long info) -> {
                log.trace("상품 설명을 찾을 수 없습니다. pk:{}", info);
                return NotFoundProductDescriptionException::new;
            };
        }
    }
}

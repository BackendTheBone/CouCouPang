package p.khj745700.coucoupang.application.dao;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import p.khj745700.coucoupang.application.domain.product.Product;
import p.khj745700.coucoupang.application.domain.product.ProductRepository;
import p.khj745700.coucoupang.application.exception.CustomException;
import p.khj745700.coucoupang.application.exception.ProductNotFoundException;

import java.util.function.Function;
import java.util.function.Supplier;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProductDao {

    private final ProductRepository productRepository;

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public Product findById(Long id) {
        return productRepository.findById(id).orElseThrow(ProductDao.ExceptionHandler.NOT_FOUND.apply(id));
    }

    @Getter
    private static class ExceptionHandler {

        private static final Function<Long, Supplier<CustomException>> NOT_FOUND;

        static {
            NOT_FOUND = (Long info) -> {
                log.trace("상품을 찾을 수 없습니다. pk:{}", info);
                return ProductNotFoundException::new;
            };
        }

    }

}

package p.khj745700.coucoupang.application.dao;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import p.khj745700.coucoupang.application.domain.product.Product;
import p.khj745700.coucoupang.application.domain.product.ProductRepository;
import p.khj745700.coucoupang.application.dto.request.product.ProductDto;
import p.khj745700.coucoupang.application.exception.CustomException;
import p.khj745700.coucoupang.application.exception.DuplicateMemberException;
import p.khj745700.coucoupang.application.exception.NotFoundMemberException;

import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

@Component
@RequiredArgsConstructor
@Slf4j
public class ProductDao {

    private final ProductRepository productRepository;

    public Product save(Product product) {
        return productRepository.save(product);
    }


    @Getter
    private static class ExceptionHandler {
        private static final Function<String, Supplier<CustomException>> NOT_FOUND;
        private static final Function<String, Supplier<CustomException>> DUPLICATE;
        static {
            NOT_FOUND = (String info) -> {
                log.trace("사용자를 찾을 수 없습니다. pk:{}", info);
                return NotFoundMemberException::new;
            };
            DUPLICATE = (String info) -> {
                log.trace("사용자가 이미 존재합니다. pk:{}", info);
                return DuplicateMemberException::new;
            };
        }
    }
}

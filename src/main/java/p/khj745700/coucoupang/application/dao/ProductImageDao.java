package p.khj745700.coucoupang.application.dao;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import p.khj745700.coucoupang.application.domain.img.Image;
import p.khj745700.coucoupang.application.domain.product.Product;
import p.khj745700.coucoupang.application.domain.product.ProductImage;
import p.khj745700.coucoupang.application.domain.product.ProductImageRepository;
import p.khj745700.coucoupang.application.domain.product.description.ProductDescriptionImage;
import p.khj745700.coucoupang.application.exception.CustomException;
import p.khj745700.coucoupang.application.exception.DuplicateMemberException;
import p.khj745700.coucoupang.application.exception.NotFoundMemberException;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;


@Component
@RequiredArgsConstructor
@Slf4j
public class ProductImageDao {

    private final ProductImageRepository productImageRepository;

    public ProductImage save(ProductImage productImage) {
        return productImageRepository.save(productImage);
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

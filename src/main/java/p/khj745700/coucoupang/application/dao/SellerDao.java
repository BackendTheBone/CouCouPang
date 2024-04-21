package p.khj745700.coucoupang.application.dao;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import p.khj745700.coucoupang.application.domain.member.Seller;
import p.khj745700.coucoupang.application.domain.member.SellerRepository;
import p.khj745700.coucoupang.application.exception.CustomException;
import p.khj745700.coucoupang.application.exception.DuplicateMemberException;
import p.khj745700.coucoupang.application.exception.NotFoundMemberException;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

@Component
@RequiredArgsConstructor
@Slf4j
public class SellerDao {

    private final SellerRepository sellerRepository;
    public void delete(String username) {
        Optional<Seller> byUsername = findByUsername(username);
        if(!isExists(byUsername)) {
            return;
        }
        sellerRepository.delete(byUsername.get());
    }

    public Optional<Seller> findByUsername(String username) {
        return sellerRepository.findById(username);
    }

    public Seller findByUsernameIfNotExistThrowException(String username) {
        return sellerRepository.findById(username).orElseThrow(ExceptionHandler.NOT_FOUND.apply(username));
    }

    private boolean isExists(Optional<Seller> sellerOptional) {
        return sellerOptional.isPresent();
    }

    @Getter
    private static class ExceptionHandler {
        private static final Function<String, Supplier<CustomException>> NOT_FOUND;
        private static final Function<String, Supplier<CustomException>> DUPLICATE;
        static {
            NOT_FOUND = (String info) -> {
                log.trace("판매자를 찾을 수 없습니다. pk:{}", info);
                return NotFoundMemberException::new;
            };
            DUPLICATE = (String info) -> {
                log.trace("판매자가 이미 존재합니다. pk:{}", info);
                return DuplicateMemberException::new;
            };
        }
    }
}

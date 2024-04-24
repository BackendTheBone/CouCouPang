package p.khj745700.coucoupang.application.domain.product.description;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductDescriptionRepository extends JpaRepository<ProductDescription, Long> {

    Optional<ProductDescription> findProductDescriptionByProduct_Id(Long productId);
}

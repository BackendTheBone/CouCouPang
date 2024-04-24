package p.khj745700.coucoupang.application.domain.product.description;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductDescriptionImageRepository extends JpaRepository<ProductDescriptionImage, Long> {

    public List<ProductDescriptionImage> findByProductDescription_Id(Long productDescriptionId);

}

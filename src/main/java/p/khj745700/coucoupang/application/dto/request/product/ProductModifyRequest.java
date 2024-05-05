package p.khj745700.coucoupang.application.dto.request.product;

import lombok.*;
import p.khj745700.coucoupang.application.domain.product.Product;
import p.khj745700.coucoupang.application.domain.product.ProductState;
import p.khj745700.coucoupang.application.domain.product.description.ProductDescription;
import p.khj745700.coucoupang.application.dto.request.img.ImageModifyRequest;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ProductModifyRequest {
    private Long productId;
    private List<ImageModifyRequest> mainImages;
    private List<ImageModifyRequest> descriptionImages;
    private String color;
    private Integer price;
    private ProductState productState;
    private String description;
    private Integer stock;

    public Product toProductEntity() {
        return Product.builder()
                .state(productState)
                .price(price)
                .stock(stock)
                .build();
    }

    public ProductDescription toProductDescriptionEntity() {
        return ProductDescription.builder()
                .color(color)
                .description(description)
                .build();
    }
}

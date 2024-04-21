package p.khj745700.coucoupang.application.dto.request.product;

import lombok.*;
import p.khj745700.coucoupang.application.domain.member.Seller;
import p.khj745700.coucoupang.application.domain.product.Product;
import p.khj745700.coucoupang.application.domain.product.ProductState;
import p.khj745700.coucoupang.application.domain.product.description.ProductDescription;
import p.khj745700.coucoupang.application.dto.request.img.ImageUploadRequest;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductRegisterRequest {
    private String name;
    private List<ImageUploadRequest> mainImages;
    private List<ImageUploadRequest> descriptionImages;
    private String color;
    private Integer price;
    private ProductState productState;
    private String description;
    private Integer stock;

    public Product toProductEntity(Seller seller) {
        return Product.builder()
                .state(productState)
                .name(name)
                .price(price)
                .seller(seller)
                .stock(stock)
                .build();
    }

    public ProductDescription toProductDescriptionEntity(Product product) {
        return ProductDescription.builder()
                .color(color)
                .description(description)
                .product(product)
                .build();
    }
}

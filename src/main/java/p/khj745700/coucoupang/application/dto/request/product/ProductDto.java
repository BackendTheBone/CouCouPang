package p.khj745700.coucoupang.application.dto.request.product;

import lombok.*;
import p.khj745700.coucoupang.application.domain.product.ProductState;
import p.khj745700.coucoupang.application.dto.request.member.seller.SellerDto;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDto {
    private Long productId;
    private String name;
    private Integer price;
    private Integer stock;
    private ProductState productState;
    private SellerDto seller;;
}

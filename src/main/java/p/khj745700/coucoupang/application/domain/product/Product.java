package p.khj745700.coucoupang.application.domain.product;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SQLDelete;
import p.khj745700.coucoupang.application.domain.common.CommonEntity;
import p.khj745700.coucoupang.application.domain.member.Seller;
import p.khj745700.coucoupang.application.exception.ProductNotEqualsSellerException;
import p.khj745700.coucoupang.application.exception.ProductStateCantSellException;

@Entity
@Table
@NoArgsConstructor
@Getter
@SuperBuilder
@SQLDelete(sql = "UPDATE product SET product_state = 'END' WHERE username = ?")
public class Product extends CommonEntity {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private Integer price;

    @Column
    private Integer stock;

    @Column
    @Enumerated(EnumType.STRING)
    private ProductState state;

    @JoinColumn
    @ManyToOne
    private Seller seller;

    public void validSameSeller(Seller seller) {
        if (!this.seller.equals(seller)) {
            throw new ProductNotEqualsSellerException();
        }
    }

    public void modifyProduct(Product modifyTarget) {
        if (modifyTarget.stock != null) {
            stock += modifyTarget.stock;
        }
        if (modifyTarget.state != null) {
            validStateToSelling(modifyTarget.state);
            state = modifyTarget.getState();
        }
        if (modifyTarget.price != null) {
            this.price = modifyTarget.price;
        }
    }

    public void addStock(Integer quantity) {
        this.stock += quantity;
    }

    public Integer removeStock(Integer quantity) {

        validStateToSelling(this.state);

        int maximumAllowableQuantity = Math.min(this.stock, quantity);
        this.stock -= maximumAllowableQuantity;
        return maximumAllowableQuantity;
    }

    private void validStateToSelling(ProductState newProductState) {
        if (stock == 0 && newProductState.equals(ProductState.SELLING)) {
            throw new ProductStateCantSellException();
        }
    }

}

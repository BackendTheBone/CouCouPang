package p.khj745700.coucoupang.application.domain.payment.product;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import p.khj745700.coucoupang.application.domain.common.CommonEntity;
import p.khj745700.coucoupang.application.domain.payment.Payment;
import p.khj745700.coucoupang.application.domain.product.Product;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PayProduct extends CommonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pay_product_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_id")
    private Payment payment;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    private Integer count;

    private Integer price;

    @Enumerated(EnumType.STRING)
    private PayProductStatus status;

    @Builder
    private PayProduct(Payment payment, Product product, Integer count) {
        this.payment = payment;
        this.product = product;
        this.count = count;
        this.price = product.getPrice() * count;
        this.status = PayProductStatus.PAYMENT_WAITING;

        product.removeStock(count);
    }

    public void cancel() {
        getProduct().addStock(count);
        status = PayProductStatus.CANCELED;
    }

}

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

        Integer maximumAllowableCount = product.removeStock(count);

        this.payment = payment;
        this.product = product;
        this.count = maximumAllowableCount;
        this.price = product.getPrice() * count;
        this.status = PayProductStatus.PAYMENT_WAITING;
    }

    public void paymentWaiting() {
        this.status = PayProductStatus.PAYMENT_WAITING;
    }

    public void preparing() {
        this.status = PayProductStatus.PREPARING;
    }

    public void shipped() {
        this.status = PayProductStatus.SHIPPED;
    }

    public void delivering() {
        this.status = PayProductStatus.DELIVERING;
    }

    public void deliveryCompleted() {
        this.status = PayProductStatus.DELIVERY_COMPLETED;
    }

    public void canceled() {
        getProduct().addStock(count);
        this.status = PayProductStatus.CANCELED;
    }

}

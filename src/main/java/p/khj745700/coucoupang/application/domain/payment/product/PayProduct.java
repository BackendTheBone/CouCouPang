package p.khj745700.coucoupang.application.domain.payment.product;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import p.khj745700.coucoupang.application.domain.common.CommonEntity;
import p.khj745700.coucoupang.application.domain.payment.Payment;
import p.khj745700.coucoupang.application.domain.product.Product;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder
public class PayProduct extends CommonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pay_product_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "payment_id")
    private Payment payment;

    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private Integer count;

    private Integer price;

    @Enumerated(EnumType.STRING)
    private PayProductStatus status;

}

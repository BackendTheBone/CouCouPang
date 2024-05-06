package p.khj745700.coucoupang.application.domain.order.item;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import p.khj745700.coucoupang.application.domain.common.CommonEntity;
import p.khj745700.coucoupang.application.domain.order.Order;
import p.khj745700.coucoupang.application.domain.product.Product;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItem extends CommonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    private Integer price;

    private Integer quantity;

    @Enumerated(EnumType.STRING)
    private OrderItemStatus status;

    @Builder
    public OrderItem(Order order, Product product, Integer quantity) {

        Integer maximumAllowableQuantity = product.removeStock(quantity);

        this.order = order;
        this.product = product;
        this.price = product.getPrice();
        this.quantity = maximumAllowableQuantity;
        this.status = OrderItemStatus.PAYMENT_WAITING;
    }

    public void addQuantity(Integer difference) {
        this.quantity += difference;
    }

    public void removeQuantity(Integer difference) {
        this.quantity -= difference;
    }

    public void preparing() {
        this.status = OrderItemStatus.PREPARING;
    }

    public void canceled() {
        this.product.addStock(this.quantity);
        this.status = OrderItemStatus.CANCELED;
    }

}

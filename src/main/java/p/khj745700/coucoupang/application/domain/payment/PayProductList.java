package p.khj745700.coucoupang.application.domain.payment;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import p.khj745700.coucoupang.application.domain.common.CommonEntity;
import p.khj745700.coucoupang.application.domain.product.ProductDescription;

@Entity
@Table
@NoArgsConstructor
@Getter
@SuperBuilder
public class PayProductList extends CommonEntity {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JoinColumn
    @ManyToOne
    private ProductDescription productDescription;


}

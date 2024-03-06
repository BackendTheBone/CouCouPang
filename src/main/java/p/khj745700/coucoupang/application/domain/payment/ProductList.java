package p.khj745700.coucoupang.application.domain.payment;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import p.khj745700.coucoupang.application.domain.common.CommonEntity;

@Entity
@Table
@NoArgsConstructor
@Getter
@SuperBuilder
public class ProductList extends CommonEntity {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JoinColumn
    @ManyToOne
    private Payment payment;

    @JoinColumn
    @ManyToOne
    private ProductList product;
}

package p.khj745700.coucoupang.application.domain.product;

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
public class Product extends CommonEntity {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private Integer price;

    @Column
    @Enumerated
    private ProductState state;

    @JoinColumn
    @OneToOne
    private ProductDescription description;
}

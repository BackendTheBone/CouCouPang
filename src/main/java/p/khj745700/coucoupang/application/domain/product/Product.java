package p.khj745700.coucoupang.application.domain.product;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import p.khj745700.coucoupang.application.domain.common.CommonEntity;
import p.khj745700.coucoupang.application.domain.common.SoftDeleteEntity;
import p.khj745700.coucoupang.application.domain.member.Seller;
import p.khj745700.coucoupang.application.domain.product.description.ProductDescription;

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

}

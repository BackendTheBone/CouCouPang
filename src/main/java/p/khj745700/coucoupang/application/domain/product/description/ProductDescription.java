package p.khj745700.coucoupang.application.domain.product.description;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SQLDelete;
import p.khj745700.coucoupang.application.domain.common.CommonEntity;
import p.khj745700.coucoupang.application.domain.member.Member;
import p.khj745700.coucoupang.application.domain.member.Seller;
import p.khj745700.coucoupang.application.domain.product.Product;

@Entity
@Table
@NoArgsConstructor
@Getter
@SuperBuilder
public class ProductDescription extends CommonEntity {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    @Column
    private String description;

    @JoinColumn
    @OneToOne
    private Product product;


    @Column
    private String color;
}

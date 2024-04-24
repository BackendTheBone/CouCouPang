package p.khj745700.coucoupang.application.domain.product.description;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import p.khj745700.coucoupang.application.domain.common.CommonEntity;
import p.khj745700.coucoupang.application.domain.common.SoftDeleteEntity;
import p.khj745700.coucoupang.application.domain.member.Member;
import p.khj745700.coucoupang.application.domain.member.Seller;
import p.khj745700.coucoupang.application.domain.product.Product;

@Entity
@Table
@NoArgsConstructor
@Getter
@SuperBuilder
@SQLDelete(sql ="UPDATE product SET is_deleted = true WHERE id = ?")
@Where(clause = "WHERE is_delete = false ")
public class ProductDescription extends SoftDeleteEntity {
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


    public void modifyProductDescription(ProductDescription modifyTarget) {
        if(modifyTarget.color != null) {
            color = modifyTarget.color;
        }

        if(modifyTarget.description != null) {
            description = modifyTarget.getDescription();
        }
    }
}

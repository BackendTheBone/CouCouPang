package p.khj745700.coucoupang.application.domain.product;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import p.khj745700.coucoupang.application.domain.common.CommonEntity;
import p.khj745700.coucoupang.application.domain.common.SoftDeleteEntity;
import p.khj745700.coucoupang.application.domain.img.Image;

@Entity
@Table
@NoArgsConstructor
@SuperBuilder
@Getter
@SQLDelete(sql ="UPDATE product_image SET is_deleted = true WHERE id = ?")
@Where(clause = "WHERE is_delete = false ")
public class ProductImage extends SoftDeleteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @JoinColumn
    @ManyToOne
    private Product product;

    @JoinColumn
    @ManyToOne(cascade = CascadeType.REMOVE)
    private Image image;

    @Column
    private Integer order;
}

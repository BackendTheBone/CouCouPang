package p.khj745700.coucoupang.application.domain.product.description;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import p.khj745700.coucoupang.application.domain.common.CommonEntity;
import p.khj745700.coucoupang.application.domain.img.Image;

@Entity
@Table
@NoArgsConstructor
@SuperBuilder
@Getter
public class ProductDescriptionImage extends CommonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @JoinColumn
    @ManyToOne
    private ProductDescription productDescription;

    @JoinColumn
    @ManyToOne
    private Image image;

    @Column
    private Integer order;
}

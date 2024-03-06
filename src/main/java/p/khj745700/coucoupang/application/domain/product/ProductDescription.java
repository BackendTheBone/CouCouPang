package p.khj745700.coucoupang.application.domain.product;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import p.khj745700.coucoupang.application.domain.common.CommonEntity;
import p.khj745700.coucoupang.application.domain.member.Member;

@Entity
@Table
@NoArgsConstructor
@Getter
@SuperBuilder
public class ProductDescription extends CommonEntity {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @Column
    private String description;

    @JoinColumn
    @ManyToOne
    private Member seller;

    @Column
    private String color;
}

package p.khj745700.coucoupang.application.domain.member;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import p.khj745700.coucoupang.application.domain.common.SoftDeleteEntity;

@Table
@Entity
@NoArgsConstructor
@SuperBuilder
@Getter
public class Seller extends SoftDeleteEntity {
    @Id
    @Column(name = "seller_id")
    private String id;

    @MapsId
    @JoinColumn(name = "seller_id", referencedColumnName = "member_id")
    @OneToOne
    private Member member;

    @Column(name = "market_name")
    private String marketName;
}

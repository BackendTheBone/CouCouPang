package p.khj745700.coucoupang.application.domain.payment;


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
public class Payment extends CommonEntity {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.UUID)
    private String paymentId;

    @ManyToOne
    @JoinColumn
    private Member buyer;

    @ManyToOne
    @JoinColumn
    private Member seller;


}


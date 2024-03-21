package p.khj745700.coucoupang.application.domain.member;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SQLDelete;
import p.khj745700.coucoupang.application.domain.common.CommonEntity;
import p.khj745700.coucoupang.application.domain.common.SoftDeleteEntity;

@Entity
@Table
@NoArgsConstructor
@Getter
@SuperBuilder
@SQLDelete(sql = "UPDATE member SET is_deleted = true WHERE username = ?")
public class Member extends SoftDeleteEntity {
    @Id
    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "phone_num")
    private String phoneNum;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;
    //Seller OneToOne으로 대상을 가져오지 않는 이유?
    /**
     * @OneToOne(cascade = CascadeType.ALL)
     * private Seller seller;
     *
     * OneToOne의 연관관계 주인은 Seller가 들고 있게 된다.
     *
     * 따라서 Seller는 OneToOne 으로 가져온다고 가정했을 때 FetchType.LAZY로 설정해도 동작하게 된다.
     * 그러나, Member(소비자 및 판매자 모두)는 Seller를 참조하는 객체이며, 실질적인 컬럼이 없기 때문에  프록시는 null을 줘야할지, 프록시 객체를 줘야할지 알 수 없음.
     * 따라서 Eager로 판단해야 하는 문제가 발생하는 것임.
     *
     * 그러므로, 멤버 조회가 많은 객체 특성상, Member는 계속 Seller를 Join해야 하는 사이드 이펙트가 발생함.
     * 따라서 시스템 성능 상 연관관계를 지우는 게 맞다고 판단됨.
     *
     * 단, 사용자가 많지 않은 시스템의 경우 고려해야 될 대상은 아니라고도 판단됨.
     */
//

}

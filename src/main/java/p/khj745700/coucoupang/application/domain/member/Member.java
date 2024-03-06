package p.khj745700.coucoupang.application.domain.member;

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
public class Member extends CommonEntity {
    @Id
    @Column
    private String username;

    @Column
    private String password;

    @Column
    private String nickname;

    @Column
    @Enumerated
    private MemberType type;
}

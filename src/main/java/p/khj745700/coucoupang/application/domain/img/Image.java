package p.khj745700.coucoupang.application.domain.img;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import p.khj745700.coucoupang.application.domain.common.CommonEntity;
import p.khj745700.coucoupang.application.domain.common.SoftDeleteEntity;

@Entity
@Table
@Getter
@NoArgsConstructor
@SuperBuilder
public class Image extends CommonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String src;

    public int getYear() {
        return super.getCreatedAt().getYear();
    }

    public int getMonth() {
        return super.getCreatedAt().getMonthValue();
    }
}

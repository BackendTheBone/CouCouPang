package p.khj745700.coucoupang.application.domain.common;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


@MappedSuperclass
@SuperBuilder
@NoArgsConstructor
public abstract class SoftDeleteEntity extends CommonEntity{
    @Column(name = "is_deleted", columnDefinition = "boolean default false")
    private Boolean isDeleted;

    @PrePersist
    private void noDeleted(){
        if(isDeleted != null) {
            return;
        }
        isDeleted = false;
    } //JPA 영속성 추가되면 default로 추가되게 함. 궂이 false 일일이 추가 안해도 됨.

}

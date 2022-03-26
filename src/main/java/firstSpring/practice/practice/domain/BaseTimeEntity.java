package firstSpring.practice.practice.domain;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass //  jpa entity 클래스들이 baseTimeEntity 상속할 경우 createdDate, modifiedDate 까지 칼럼으로 인식
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseTimeEntity {
    @CreatedDate // entity 생성, 저장될 때 시간 자동 저장.
    private LocalDateTime createdDate;

    @LastModifiedDate // 조회한 entity 값 변경시 시간 자동 저장.
    private LocalDateTime modifiedDate;
}

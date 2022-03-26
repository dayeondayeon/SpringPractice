package firstSpring.practice.practice.domain.posts;

import firstSpring.practice.practice.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter // getter 메소드 자동 생성. 롬복의 어노테이션들이 서비스 초기 단계 테이블 빈번한 변경에서 코드 변경량 최소화시켜줌.
@NoArgsConstructor //위의 두개는 lombok, 얘는 기본 생성자 자동 추가. public Posts() {}와 같은 효과.
@Entity // JPA annotation

public class Posts extends BaseTimeEntity { // 실제 DB와 매칭될 클래스, 직접 쿼리 날리는 것이 아닌 entity 수정을 통해 작업
    @Id // 해당 테이블의 PK, 가능한 pk는 long type, auto_increment : mysql ~ bigint
    @GeneratedValue(strategy = GenerationType.IDENTITY) // PK 생성 규칙
    private Long id;

    @Column(length = 500, nullable = false) //기본적으로 필드는 다 column, 옵션 주고 싶을 때 사용.
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder // 생성 시점에 값을 채워주는 역할, 생성자와 동일.
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
} // 기본적으로 entity 클래스에서는 setter 생성 X, 필드 값 변경이 필요하면 목적과 의도를 나타내는 메소드를 추가해야 함.
// 생성자를 통해 최종 값 채운 후 DB에 삽입, 해당 이벤트에 맞는 public 메소드 호출.
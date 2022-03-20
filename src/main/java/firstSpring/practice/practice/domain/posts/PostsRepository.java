package firstSpring.practice.practice.domain.posts;
import org.springframework.data.jpa.repository.JpaRepository;

// DB Layer 접근자, Repository 라고 부르고 interface 생성
// 이후 JpaRepository<Entity 클래스, PK 타입> 상속시 CRUD 메소드 자동 생성
// Entity 클래스와 Entity Repository 함께 위치해야 한다. 도메인 패키지에서 함께 관리.

public interface PostsRepository extends JpaRepository<Posts, Long> {

}

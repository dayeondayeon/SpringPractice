package firstSpring.practice.domain.posts;
import firstSpring.practice.practice.domain.posts.Posts;
import firstSpring.practice.practice.domain.posts.PostsRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest // H2 데이터베이스 자동 실행.
public class PostsRepositoryTest {
    @Autowired
    PostsRepository postsRepository;

    @After // 단위 테스트가 끝날 때마다 수행하는 메소드
    public void cleanup() {
        postsRepository.deleteAll(); // 테스트 용으로 들어간 데이터들 모두 삭제
    }

    @Test
    public void getPostList() {
        String title = "테스트 게시글";
        String content = "테스트 본문";

        postsRepository.save(Posts.builder()// insert, update 쿼리 실행. id값 있으면 update, 없으면  insert
                .title(title)
                .content(content)
                .author("jojoldu@gmail.com")
                .build());

        List<Posts> postsList = postsRepository.findAll(); // 모든 데이터 조회

        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }

    @Test
    public void BaseTimeEntity_등록() {
        LocalDateTime now = LocalDateTime.of(2022,3,26,0,0,0);
        postsRepository.save(Posts.builder()
                .title("title")
                .content("content")
                .author("author")
                .build());

        List<Posts> postsList = postsRepository.findAll();
        Posts posts = postsList.get(0);
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>> createdDate = " + posts.getCreatedDate()+" modified Date = " + posts.getModifiedDate());
        assertThat(posts.getCreatedDate()).isAfter(now);
        assertThat(posts.getModifiedDate()).isAfter(now);
    }
}

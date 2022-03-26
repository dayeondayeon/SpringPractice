package firstSpring.practice.practice.service.posts;
import firstSpring.practice.practice.domain.posts.Posts;
import firstSpring.practice.practice.domain.posts.PostsRepository;
import firstSpring.practice.practice.web.dto.PostsResponseDto;
import firstSpring.practice.practice.web.dto.PostsSaveRequestDto;
import firstSpring.practice.practice.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+id));
        posts.update(requestDto.getTitle(), requestDto.getContent());
        return id;
    } // update 에서 왜 쿼리를 날리는 부분이 없는가?

    public PostsResponseDto findById (Long id) {
        Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+id));
        return new PostsResponseDto(entity);
    }
}
// jpa : 영속성 컨텍스트 (엔티티를 영구 저장하는 환경)
// jpa 의 엔티티 매니저가 활성화된 상태로 트랜잭션안에서 데이터베이스에서 데이터 가져오면 영속성 컨텍스트 유지 상태임.
// 트랜잭션이 끝나는 시점에 해당 테이블에 변경분 반영, update 쿼리 별도로 날리지 않아도 됨.
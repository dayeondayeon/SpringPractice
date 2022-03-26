package firstSpring.practice.practice.web.dto;
import firstSpring.practice.practice.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsSaveRequestDto {
    private String title;
    private String content;
    private String author;

    @Builder
    public PostsSaveRequestDto(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public Posts toEntity() {
        return Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }
} // Entity 클래스와 거의 유사, Dto 클래스 추가 생성 : Entity 는 db와 맞닿은 핵심 클래스, request,response 로 사용해서는 안됨.
// response, request 용의 dto는 view를 위한 클래스라 자주 변경 필요. 그래서 view layer와 db layer 역할을 철저히 분리.

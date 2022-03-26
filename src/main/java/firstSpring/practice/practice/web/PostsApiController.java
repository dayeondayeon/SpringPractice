package firstSpring.practice.practice.web;
import firstSpring.practice.practice.web.dto.PostsResponseDto;
import firstSpring.practice.practice.web.dto.PostsSaveRequestDto;
import firstSpring.practice.practice.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import firstSpring.practice.practice.service.posts.PostsService;

@RequiredArgsConstructor
@RestController
public class PostsApiController {
    private final PostsService postsService;
    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto) {
        return postsService.save(requestDto);
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto) {
        return postsService.update(id, requestDto);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById (@PathVariable Long id) {
        return postsService.findById(id);
    }
}

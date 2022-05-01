package firstSpring.practice.practice.web;
import firstSpring.practice.practice.config.auth.LoginUser;
import firstSpring.practice.practice.config.auth.dto.SessionUser;
import firstSpring.practice.practice.service.posts.PostsService;
import firstSpring.practice.practice.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) {
        model.addAttribute("posts", postsService.findAllDesc());
        if (user != null) {
            model.addAttribute("userName", user.getName());
        }
        return "index"; // 문자열을 반환할 때 앞의 경로, 뒤 확장자는 자동지정됨.
        // 즉, src/main/resources/templates 까지는 자동, return 문의 index, 그 뒤 다시 자동으로 .mustache 확장자
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save"; // posts/save 호출하면 posts-save.mustache 호출하는 메소드 추가
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);
        return "posts-update";
    }
} //URL 매핑을 위한 controller

package firstSpring.practice.web;
import firstSpring.practice.practice.web.HelloController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)  // Junit에 내장된 실행자 외에 SpringRunner라는 실행자 사용. SpringBoot-JUnit연결
@WebMvcTest(controllers = HelloController.class) // @Controller, @ControllerAdvice등 사용 가능
public class HelloControllerTest {

    @Autowired // 스프링이 관리하는 빈을 주입받음
    private MockMvc mvc; // 스프링 mvc 테스트의 시작점, 이 클래스를 통해 http메소드 API테스트 가능

    @Test
    public void hello가_리턴된다() throws Exception {
        String hello = "hello";
        mvc.perform(get("/hello")) // MockMvc를 통해 /hello주소로 get요청
                .andExpect(status().isOk()) //status 검증
                .andExpect(content().string(hello)); // content 검증(hello리턴 맞니?)
    }
}

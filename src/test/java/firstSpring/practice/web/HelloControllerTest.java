package firstSpring.practice.web;
import firstSpring.practice.practice.config.auth.SecurityConfig;
import firstSpring.practice.practice.web.HelloController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)  // Junit에 내장된 실행자 외에 SpringRunner라는 실행자 사용. SpringBoot-JUnit연결
@WebMvcTest(controllers = HelloController.class, excludeFilters = {@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class)}) // @Controller, @ControllerAdvice등 사용 가능
public class HelloControllerTest {

    @Autowired // 스프링이 관리하는 빈을 주입받음
    private MockMvc mvc; // 스프링 mvc 테스트의 시작점, 이 클래스를 통해 http메소드 API테스트 가능

    @WithMockUser(roles="USER")
    @Test
    public void hello가_리턴된다() throws Exception {
        String hello = "hello";
        mvc.perform(get("/hello")) // MockMvc를 통해 /hello주소로 get요청
                .andExpect(status().isOk()) //status 검증
                .andExpect(content().string(hello)); // content 검증(hello리턴 맞니?)
    }

    @WithMockUser(roles="USER")
    @Test
    public void helloDto_return() throws Exception {
        String name = "hello";
        int amount = 1000;

        mvc.perform(get("/hello/dto")
                        .param("name", name) // param : api 테스트 시 사용될 요청 파라미터 설정, 문자열만 가능
                        .param("amount", String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name))) // jsonPath : $기준으로 필드명 명시, json 응답값을 필드별로 검증
                .andExpect(jsonPath("$.amount", is(amount)));
    }
}

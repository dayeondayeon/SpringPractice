package firstSpring.practice.practice.config.auth;
import firstSpring.practice.practice.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity // security 설정 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure (HttpSecurity http) throws Exception {
        http.csrf().disable().headers().frameOptions().disable()// h2-console 화면 사용을 위한 옵션 disable
                .and()
                .authorizeRequests()// URL 별 권한 관리 설정 옵션의 시작점!
                .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**").permitAll() // permitAll : 전체 열람 권한
                .antMatchers("/api/v1/**").hasRole(Role.USER.name()) //USER 권한 가진 사람만 열람 가능
                .anyRequest().authenticated() // 나머지 URL ~ 모두 인증된(로그인된) 사용자들만 이용 가능하게
                .and()
                .logout()
                .logoutSuccessUrl("/") // 로그아웃 성공시 "/"로 이동
                .and()
                .oauth2Login()//로그인 기능 진입점
                .userInfoEndpoint() // 사용자 정보 가져올 때의 설정
                .userService(customOAuth2UserService); // 로그인 성공 이후 진행할 인터페이스 구현체 등록
    }
}

package firstSpring.practice.web.dto;
import firstSpring.practice.practice.web.dto.HelloResponseDto;
import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class HelloResponseDtoTest {
    @Test
    public void lombok_test() {
        //given
        String name = "test";
        int amount = 1000;

        //when
        HelloResponseDto dto = new HelloResponseDto(name, amount);

        //thenA
        // assertThat : assertj(테스트 검증 라이브러리)의 검증 메소드
        assertThat(dto.getName()).isEqualTo(name); // 검증하고 싶은 대상을 메소드 인자로 받음, isEqualTo와 비교
        assertThat(dto.getAmount()).isEqualTo(amount);
    }
}

package softeer2nd.common.util;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("문자열 유틸리티 관련 기능")
class StringUtilsTest {

    @DisplayName("문자열 끝에 줄바꿈을 추가한다.")
    @Test
    void appendNewLine() {
        String text = "Hello world!";

        assertThat(StringUtils.appendNewLine(text)).isEqualTo("Hello world!\n");
    }
}

package softeer2nd.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("폰 관련 기능")
public class PawnTest {

    @Test
    @DisplayName("흰색 폰이 생성되어야 한다")
    public void create() {
        Pawn pawn = new Pawn("white");

        assertThat(pawn.getColor()).isEqualTo("white");
    }

    @DisplayName("폰의 색상을 가져온다.")
    @ParameterizedTest(name = "color : {0}")
    @ValueSource(strings = {"white", "black"})
    void getColor(final String color) {
        Pawn pawn = new Pawn(color);

        assertThat(pawn.getColor()).isEqualTo(color);
    }
}

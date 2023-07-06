package softeer2nd.domain.chess.pieces;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("위치 관련 기능")
class PositionTest {

    @DisplayName("위치를 생성한다.")
    @Test
    void create() {
        Position position = new Position("a8");

        assertAll(
                () -> assertThat(position.getX()).isEqualTo(0),
                () -> assertThat(position.getY()).isEqualTo(0)
        );
    }

    @DisplayName("입력받은 문자열의 길이가 2가 아닐 경우 예외 처리한다.")
    @Test
    void createInputException() {
        assertAll(
                () -> assertThatThrownBy(() -> new Position("a12")).isInstanceOf(IllegalArgumentException.class),
                () -> assertThatThrownBy(() -> new Position("ab1")).isInstanceOf(IllegalArgumentException.class)
        );
    }

    @DisplayName("위치 범위를 벗어날 경우 예외처리한다.")
    @Test
    void createOutOfBound() {
        assertAll(
                () -> assertThatThrownBy(() -> new Position("a9")).isInstanceOf(IllegalArgumentException.class),
                () -> assertThatThrownBy(() -> new Position("i5")).isInstanceOf(IllegalArgumentException.class)
        );
    }
}

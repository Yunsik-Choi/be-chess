package softeer2nd.domain.chess.pieces;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("좌표 관련 기능")
public class PointTest {

    @DisplayName("좌표를 생성한다.")
    @Test
    void create() {
        Point point = new Point(0, 1);

        assertAll(
                () -> assertThat(point.getX()).isEqualTo(0),
                () -> assertThat(point.getY()).isEqualTo(1)
        );
    }

    @DisplayName("좌표는 음수가 될 수 없다.")
    @Test
    void createMinus() {
        assertAll(
                () -> assertThatThrownBy(() -> new Point(-1, 0)).isInstanceOf(IllegalArgumentException.class),
                () -> assertThatThrownBy(() -> new Point(0, -1)).isInstanceOf(IllegalArgumentException.class)
        );
    }

    @DisplayName("좌표의 X 값을 가져온다.")
    @Test
    void getX() {
        Point point = new Point(0, 1);

        assertThat(point.getX()).isEqualTo(0);
    }

    @DisplayName("좌표의 Y 값을 가져온다.")
    @Test
    void getY() {
        Point point = new Point(4, 3);

        assertThat(point.getY()).isEqualTo(3);
    }
}

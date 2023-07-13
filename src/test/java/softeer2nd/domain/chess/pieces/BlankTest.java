package softeer2nd.domain.chess.pieces;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import softeer2nd.domain.chess.Color;

@DisplayName("공백 관련 기능")
class BlankTest {
    private Blank blank;

    @BeforeEach
    void setUp() {
        blank = new Blank(Color.NOCOLOR, new Position(1, 1), Direction.blankDirection());
    }

    @DisplayName("공백 생성 관련 기능")
    @Nested
    class Create {
        @DisplayName("공백을 생성한다.")
        @Test
        void create() {
            assertThat(new Blank(Color.NOCOLOR, new Position(0, 1), Direction.blankDirection()))
                    .isEqualTo(new Blank(Color.NOCOLOR, new Position(0, 1), Direction.blankDirection()));
        }

        @DisplayName("공백 생성시 색상이 NOCOLOR가 아니면 예외 처리한다.")
        @Test
        void createInvalidColor() {
            assertAll(
                    () -> assertThatThrownBy(
                            () -> new Blank(Color.BLACK, new Position(0, 1), Direction.blankDirection())
                    ).isInstanceOf(IllegalArgumentException.class),
                    () -> assertThatThrownBy(
                            () -> new Blank(Color.WHITE, new Position(0, 1), Direction.blankDirection())
                    ).isInstanceOf(IllegalArgumentException.class)
            );
        }

        @DisplayName("공백 생성시 방향이 있으면 예외 처리한다.")
        @Test
        void createInvalidDirection() {
            assertThatThrownBy(() -> new Blank(Color.NOCOLOR, new Position(0, 1), Direction.everyDirection()))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }

    @DisplayName("공백의 표현 문자열을 가져온다.")
    @Test
    void getRepresentationPerPiece() {
        assertThat(blank.getRepresentationPerPiece()).isEqualTo(".");
    }

    @DisplayName("공백의 포인트를 가져온다.")
    @Test
    void getPoint() {
        assertThat(blank.getPoint()).isEqualTo(0);
    }

    @DisplayName("공백의 방향을 가져온다.")
    @Test
    void getDirections() {
        assertThat(blank.getDirections()).isEmpty();
    }
}

package softeer2nd.domain.chess.pieces;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import softeer2nd.domain.chess.Color;

@DisplayName("퀸 관련 기능")
class QueenTest {
    private Queen white;
    private Queen black;

    @BeforeEach
    void setUp() {
        black = new Queen(Color.BLACK, new Position(1, 1), Direction.everyDirection());
        white = new Queen(Color.WHITE, new Position(2, 2), Direction.everyDirection());
    }

    @DisplayName("퀸을 생성한다.")
    @Test
    void create() {
        assertAll(
                () -> assertThat(new Queen(Color.BLACK, new Position(0, 1), Direction.everyDirection()))
                        .isEqualTo(new Queen(Color.BLACK, new Position(0, 1), Direction.everyDirection())),
                () -> assertThat(new Queen(Color.BLACK, new Position(2, 2), Direction.everyDirection()))
                        .isEqualTo(new Queen(Color.BLACK, new Position(2, 2), Direction.everyDirection()))
        );
    }

    @DisplayName("퀸의 표현 문자열을 가져온다.")
    @Test
    void getRepresentationPerPiece() {
        assertAll(
                () -> assertThat(white.getRepresentationPerPiece()).isEqualTo("q"),
                () -> assertThat(black.getRepresentationPerPiece()).isEqualTo("Q")
        );
    }

    @DisplayName("퀸의 포인트를 가져온다.")
    @Test
    void getPoint() {
        assertAll(
                () -> assertThat(white.getPoint()).isEqualTo(9.0),
                () -> assertThat(black.getPoint()).isEqualTo(9.0)
        );
    }

    @DisplayName("퀸의 방향을 가져온다.")
    @Test
    void getDirections() {
        assertAll(
                () -> assertThat(white.getDirections()).isEqualTo(Direction.everyDirection()),
                () -> assertThat(black.getDirections()).isEqualTo(Direction.everyDirection())
        );
    }
}

package softeer2nd.domain.chess.pieces;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import softeer2nd.domain.chess.pieces.Piece.Color;

@DisplayName("킹 관련 기능")
class KingTest {
    private King white;
    private King black;

    @BeforeEach
    void setUp() {
        black = new King(Color.BLACK, new Position(1, 1), Direction.everyDirection());
        white = new King(Color.WHITE, new Position(2, 2), Direction.everyDirection());
    }

    @DisplayName("킹을 생성한다.")
    @Test
    void create() {
        assertAll(
                () -> assertThat(new King(Color.BLACK, new Position(0, 1), Direction.diagonalDirection()))
                        .isEqualTo(new King(Color.BLACK, new Position(0, 1), Direction.diagonalDirection())),
                () -> assertThat(new King(Color.BLACK, new Position(2, 2), Direction.diagonalDirection()))
                        .isEqualTo(new King(Color.BLACK, new Position(2, 2), Direction.diagonalDirection()))
        );
    }

    @DisplayName("킹의 표현 문자열을 가져온다.")
    @Test
    void getRepresentationPerPiece() {
        assertAll(
                () -> assertThat(white.getRepresentationPerPiece()).isEqualTo("k"),
                () -> assertThat(black.getRepresentationPerPiece()).isEqualTo("K")
        );
    }

    @DisplayName("킹의 포인트를 가져온다.")
    @Test
    void getPoint() {
        assertAll(
                () -> assertThat(white.getPoint()).isEqualTo(0),
                () -> assertThat(black.getPoint()).isEqualTo(0)
        );
    }

    @DisplayName("킹의 방향을 가져온다.")
    @Test
    void getDirections() {
        assertAll(
                () -> assertThat(white.getDirections()).isEqualTo(Direction.everyDirection()),
                () -> assertThat(black.getDirections()).isEqualTo(Direction.everyDirection())
        );
    }
}

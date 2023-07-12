package softeer2nd.domain.chess.pieces;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import softeer2nd.domain.chess.pieces.Piece.Color;

@DisplayName("비숍 관련 기능")
class BishopTest {
    private Bishop white;
    private Bishop black;

    @BeforeEach
    void setUp() {
        black = new Bishop(Color.BLACK, new Position(1, 1), Direction.diagonalDirection());
        white = new Bishop(Color.WHITE, new Position(2, 2), Direction.diagonalDirection());
    }

    @DisplayName("비숍을 생성한다.")
    @Test
    void create() {
        assertAll(
                () -> assertThat(new Bishop(Color.BLACK, new Position(0, 1), Direction.diagonalDirection()))
                        .isEqualTo(new Bishop(Color.BLACK, new Position(0, 1), Direction.diagonalDirection())),
                () -> assertThat(new Bishop(Color.BLACK, new Position(2, 2), Direction.diagonalDirection()))
                        .isEqualTo(new Bishop(Color.BLACK, new Position(2, 2), Direction.diagonalDirection()))
        );
    }

    @DisplayName("비숍의 표현 문자열을 가져온다.")
    @Test
    void getRepresentationPerPiece() {
        assertAll(
                () -> assertThat(white.getRepresentationPerPiece()).isEqualTo("b"),
                () -> assertThat(black.getRepresentationPerPiece()).isEqualTo("B")
        );
    }

    @DisplayName("비숍의 포인트를 가져온다.")
    @Test
    void getPoint() {
        assertAll(
                () -> assertThat(white.getPoint()).isEqualTo(3),
                () -> assertThat(black.getPoint()).isEqualTo(3)
        );
    }

    @DisplayName("비숍의 방향을 가져온다.")
    @Test
    void getDirections() {
        assertAll(
                () -> assertThat(white.getDirections()).isEqualTo(Direction.diagonalDirection()),
                () -> assertThat(black.getDirections()).isEqualTo(Direction.diagonalDirection())
        );
    }
}

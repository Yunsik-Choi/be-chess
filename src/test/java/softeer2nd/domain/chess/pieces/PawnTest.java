package softeer2nd.domain.chess.pieces;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import softeer2nd.domain.chess.Color;

@DisplayName("폰 관련 기능")
class PawnTest {
    private Pawn white;
    private Pawn black;

    @BeforeEach
    void setUp() {
        black = new Pawn(Color.BLACK, new Position(1, 1), Direction.blackPawnDirection());
        white = new Pawn(Color.WHITE, new Position(2, 2), Direction.whitePawnDirection());
    }

    @DisplayName("폰을 생성한다.")
    @Test
    void create() {
        assertAll(
                () -> assertThat(new Pawn(Color.BLACK, new Position(0, 1), Direction.blackPawnDirection()))
                        .isEqualTo(new Pawn(Color.BLACK, new Position(0, 1), Direction.blackPawnDirection())),
                () -> assertThat(new Pawn(Color.BLACK, new Position(2, 2), Direction.blackPawnDirection()))
                        .isEqualTo(new Pawn(Color.BLACK, new Position(2, 2), Direction.blackPawnDirection()))
        );
    }

    @DisplayName("폰의 표현 문자열을 가져온다.")
    @Test
    void getRepresentationPerPiece() {
        assertAll(
                () -> assertThat(white.getRepresentationPerPiece()).isEqualTo("p"),
                () -> assertThat(black.getRepresentationPerPiece()).isEqualTo("P")
        );
    }

    @DisplayName("폰의 포인트를 가져온다.")
    @Test
    void getPoint() {
        assertAll(
                () -> assertThat(white.getPoint()).isEqualTo(1.0),
                () -> assertThat(black.getPoint()).isEqualTo(1.0)
        );
    }

    @DisplayName("폰의 방향을 가져온다.")
    @Test
    void getDirections() {
        assertAll(
                () -> assertThat(white.getDirections()).isEqualTo(Direction.whitePawnDirection()),
                () -> assertThat(black.getDirections()).isEqualTo(Direction.blackPawnDirection())
        );
    }
}

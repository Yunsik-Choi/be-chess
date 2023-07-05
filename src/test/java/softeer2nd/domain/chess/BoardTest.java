package softeer2nd.domain.chess;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("체스 판 관련 기능")
public class BoardTest {
    private Board board;

    @BeforeEach
    void setUp() {
        this.board = new Board();
    }

    @DisplayName("체스판을 출력한다.")
    @Test
    void show() {
        board.initialize();

        assertThat(board.show()).isEqualTo(
                "........\n"
                        + "PPPPPPPP\n"
                        + "........\n"
                        + "........\n"
                        + "........\n"
                        + "........\n"
                        + "pppppppp\n"
                        + "........\n"
        );
    }
}

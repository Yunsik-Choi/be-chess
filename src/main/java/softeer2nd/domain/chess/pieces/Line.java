package softeer2nd.domain.chess.pieces;

import java.util.ArrayList;
import java.util.List;

public class Line {
    private final List<Piece> pieces = new ArrayList<>();

    public Line() {
        for (int i = 0; i < 8; i++) {
            pieces.add(null);
        }
    }

    public List<Piece> getPieces() {
        return this.pieces;
    }
}

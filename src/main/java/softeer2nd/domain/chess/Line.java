package softeer2nd.domain.chess;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import softeer2nd.domain.chess.pieces.Piece;

public class Line {
    public static final int WIDTH = 8;

    private final List<Piece> pieces = new ArrayList<>();

    public Line() {
        IntStream.range(0, WIDTH)
                .forEach(i -> pieces.add(null));
    }

    public static Line createNoPiece() {
        Line line = new Line();
        IntStream.range(0, WIDTH).forEach(i -> line.set(i, Piece.createNoPiece()));
        return line;
    }

    public List<Piece> getPieces() {
        return this.pieces;
    }

    public void set(final int index, final Piece piece) {
        if (isIndexOutOfBound(index)) {
            throw new IndexOutOfBoundsException("행의 범위를 벗어났습니다.");
        }
        pieces.set(index, piece);
    }

    private static boolean isIndexOutOfBound(final int index) {
        return index < 0 || index >= WIDTH;
    }
}

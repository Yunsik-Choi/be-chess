package softeer2nd.domain.chess;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import softeer2nd.domain.chess.pieces.Piece;
import softeer2nd.domain.chess.pieces.Piece.Color;
import softeer2nd.domain.chess.pieces.Piece.Type;

public class Rank {
    public static final int WIDTH = 8;

    private final List<Piece> pieces = new ArrayList<>();

    public Rank() {
        IntStream.range(0, WIDTH)
                .forEach(i -> pieces.add(null));
    }

    public static Rank createNoPiece() {
        Rank rank = new Rank();
        IntStream.range(0, WIDTH).forEach(i -> rank.set(i, Piece.createNoPiece()));
        return rank;
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

    public int pieceCount(final Color color, final Type type) {
        return Long.valueOf(this.pieces.stream()
                .filter(piece -> piece.getColor().equals(color) && piece.getType().equals(type))
                .count()).intValue();
    }
}

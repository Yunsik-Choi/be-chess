package softeer2nd.domain.chess;

import java.util.Comparator;
import softeer2nd.domain.chess.pieces.Piece;

public enum PieceComparator {
    SORT_BY_POINT_ASC((o1, o2) -> (int) (o1.getType().getPoint() - o2.getType().getPoint())),
    SORT_BY_POINT_DESC((o1, o2) -> (int) (o2.getType().getPoint() - o1.getType().getPoint()));

    private final Comparator<Piece> comparator;

    PieceComparator(final Comparator<Piece> comparator) {
        this.comparator = comparator;
    }

    public Comparator<Piece> getComparator() {
        return comparator;
    }
}

package softeer2nd.domain.chess;

import java.util.List;
import java.util.stream.Collectors;
import softeer2nd.domain.chess.pieces.Piece;
import softeer2nd.domain.chess.pieces.Piece.Color;
import softeer2nd.domain.chess.pieces.Position;

public interface Board {
    void initializeEmpty();

    void initialize();

    void move(final String sourcePosition, final String targetPosition);

    void move(final String position, final Piece piece);

    List<Rank> getRanks();

    default int pieceCount() {
        return getRanks().stream()
                .mapToInt(rank -> Long.valueOf(rank.getPieces().stream().filter(Piece::isNoPiece).count()).intValue())
                .sum();
    }

    default Piece findPiece(final String position) {
        Position findPiecePosition = new Position(position);

        return getRanks().get(findPiecePosition.getY()).getPiece(findPiecePosition.getX());
    }

    default List<Piece> sortByPoint(final Color color, final PieceComparator pieceComparator) {
        return getRanks().stream()
                .flatMap(rank -> rank.getPieces().stream())
                .filter(piece -> piece.getColor().equals(color))
                .sorted(pieceComparator.getComparator())
                .collect(Collectors.toUnmodifiableList());
    }
}

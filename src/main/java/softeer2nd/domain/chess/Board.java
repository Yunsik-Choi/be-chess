package softeer2nd.domain.chess;

import java.util.List;
import softeer2nd.domain.chess.pieces.Piece;
import softeer2nd.domain.chess.pieces.Piece.Color;

public interface Board {
    void initializeEmpty();

    void initialize();

    int pieceCount();

    void move(final String sourcePosition, final String targetPosition);

    Piece findPiece(final String position);

    void move(final String position, final Piece piece);

    List<Piece> sortByPoint(final Color color, final PieceComparator pieceComparator);

    List<Rank> getRanks();
}

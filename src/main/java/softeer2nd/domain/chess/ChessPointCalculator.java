package softeer2nd.domain.chess;

import softeer2nd.domain.chess.pieces.Piece.Color;

public interface ChessPointCalculator {
    double calculatePoint(final Board board, final Color color);
}

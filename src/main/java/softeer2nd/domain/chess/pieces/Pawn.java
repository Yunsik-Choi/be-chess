package softeer2nd.domain.chess.pieces;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {
    private static final String REPRESENTATION = "p";
    private static final double POINT = 1.0;

    protected Pawn(final Color color, final Position position, final List<Direction> directions) {
        super(POINT, REPRESENTATION, color, position, directions);
    }

    @Override
    public Piece create(final Position position) {
        return new Pawn(this.color, position, this.directions);
    }

    @Override
    protected void addMovablePosition(
            final Direction direction,
            final List<List<Piece>> board,
            final ArrayList<Position> result,
            final Position movePosition
    ) {
        if (isMoveEnemy(board, movePosition) || isMoveBlank(board, movePosition)) {
            result.add(movePosition);
        }
    }

    private boolean isMoveEnemy(final List<List<Piece>> board, final Position movePosition) {
        return this.position.getX() != movePosition.getX() && isEnemy(this.color, movePosition, board);
    }

    private boolean isMoveBlank(final List<List<Piece>> board, final Position movePosition) {
        return this.position.getX() == movePosition.getX() && isBlank(movePosition, board);
    }
}

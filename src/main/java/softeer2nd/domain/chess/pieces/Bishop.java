package softeer2nd.domain.chess.pieces;

import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece {
    private static final String REPRESENTATION = "b";
    private static final double POINT = 3.0;

    protected Bishop(final Color color, final Position position, final List<Direction> directions) {
        super(POINT, REPRESENTATION, color, position, directions);
    }

    @Override
    public Piece create(final Position position) {
        return new Bishop(this.color, position, this.directions);
    }

    @Override
    protected void addMovablePosition(
            final Direction direction,
            final List<List<Piece>> board,
            final ArrayList<Position> result,
            final Position movePosition
    ) {
        result.add(movePosition);
        if (isBlank(movePosition, board)) {
            result.addAll(getMovablePosition(movePosition, direction, board));
        }
    }
}

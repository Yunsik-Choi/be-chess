package softeer2nd.domain.chess.pieces;

import java.util.ArrayList;
import java.util.List;

public class Queen extends Piece {
    private static final String REPRESENTATION = "q";
    private static final double POINT = 9.0;

    protected Queen(final Color color, final Position position, final List<Direction> directions) {
        super(color, position, directions);
    }

    @Override
    public String getRepresentationPerPiece() {
        if (isBlack()) {
            return REPRESENTATION.toUpperCase();
        }
        return REPRESENTATION;
    }

    @Override
    public double getPoint() {
        return POINT;
    }

    @Override
    protected Piece create(final Position position) {
        return new Queen(this.color, position, this.directions);
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

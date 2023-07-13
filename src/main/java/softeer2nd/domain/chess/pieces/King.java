package softeer2nd.domain.chess.pieces;

import java.util.ArrayList;
import java.util.List;
import softeer2nd.domain.chess.Color;

public class King extends Piece {
    private static final String REPRESENTATION = "k";
    private static final double POINT = 0.0;

    protected King(final Color color, final Position position, final List<Direction> directions) {
        super(POINT, REPRESENTATION, color, position, directions);
    }

    @Override
    public Piece create(final Position position) {
        return new King(this.color, position, this.directions);
    }

    @Override
    protected void addMovablePosition(
            final Direction direction,
            final List<List<Piece>> board,
            final ArrayList<Position> result,
            final Position movePosition
    ) {
        result.add(movePosition);
    }
}

package softeer2nd.domain.chess.pieces;

import java.util.ArrayList;
import java.util.List;

public class Blank extends Piece {
    private static final String REPRESENTATION = ".";
    private static final double POINT = 0.0;

    protected Blank(final Color color, final Position position, final List<Direction> directions) {
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
        result.addAll(List.of());
    }
}

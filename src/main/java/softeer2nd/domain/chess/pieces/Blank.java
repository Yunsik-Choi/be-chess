package softeer2nd.domain.chess.pieces;

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
    public Piece move(final Position targetPosition) {
        return new Blank(this.color, targetPosition, this.directions);
    }
}

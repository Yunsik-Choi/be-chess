package softeer2nd.domain.chess.pieces;

import java.util.List;

public class Bishop extends Piece {
    private static final String REPRESENTATION = "b";
    private static final double POINT = 3.0;

    protected Bishop(final Color color, final Position position, final List<Direction> directions) {
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
        return new Bishop(this.color, targetPosition, this.directions);
    }
}

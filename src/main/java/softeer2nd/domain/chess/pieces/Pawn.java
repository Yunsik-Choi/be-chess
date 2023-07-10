package softeer2nd.domain.chess.pieces;

import java.util.List;

public class Pawn extends Piece {
    private static final String REPRESENTATION = "p";
    private static final double POINT = 1.0;

    protected Pawn(final Color color, final Position position, final List<Direction> directions) {
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
        return new Pawn(this.color, targetPosition, this.directions);
    }
}

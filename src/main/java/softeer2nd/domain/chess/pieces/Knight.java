package softeer2nd.domain.chess.pieces;

import java.util.List;

public class Knight extends Piece {
    private static final String REPRESENTATION = "n";
    private static final double POINT = 2.5;

    protected Knight(final Color color, final Position position, final List<Direction> directions) {
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
    public Piece move(final Position targetPosition, final List<List<Piece>> board) {
        return new Knight(this.color, targetPosition, this.directions);
    }
}

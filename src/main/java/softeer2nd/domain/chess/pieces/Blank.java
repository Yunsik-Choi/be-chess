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
    public Piece move(final Position targetPosition, final List<List<Piece>> board) {
        return this.directions.stream()
                .flatMap(direction -> getMovablePosition(this.position, direction, board).stream())
                .findFirst()
                .map(position -> new Pawn(this.color, position, directions))
                .orElseThrow(moveFailException());
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

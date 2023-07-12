package softeer2nd.domain.chess.pieces;

import java.util.ArrayList;
import java.util.List;

public class Rook extends Piece {
    private static final String REPRESENTATION = "r";
    private static final double POINT = 5.0;

    protected Rook(final Color color, final Position position, final List<Direction> directions) {
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
        validationTargetPositionEqualCurrentPosition(targetPosition);

        return this.directions.stream()
                .flatMap(direction -> getMovablePosition(this.position, direction, board).stream())
                .filter(position -> position.equals(targetPosition))
                .findFirst()
                .map(position -> new Rook(this.color, position, this.directions))
                .orElseThrow(moveFailException());
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

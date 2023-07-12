package softeer2nd.domain.chess.pieces;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

        return this.getMovablePosition(position, board).stream()
                .filter(position -> position.equals(targetPosition))
                .findFirst()
                .map(position -> new Rook(this.color, position, this.directions))
                .orElseThrow(moveFailException());
    }

    @Override
    protected List<Position> getMovablePosition(
            final Position position,
            final List<List<Piece>> board
    ) {
        return directions.stream()
                .flatMap(direction -> getMovablePosition(position, direction, board).stream())
                .collect(Collectors.toUnmodifiableList());
    }

    private List<Position> getMovablePosition(
            final Position position,
            final Direction direction,
            final List<List<Piece>> board
    ) {
        ArrayList<Position> result = new ArrayList<>();
        if (!position.canMove(direction) || isSameColor(position.move(direction), board)) {
            return result;
        }
        Position movePosition = position.move(direction);
        result.add(movePosition);
        if (isBlank(movePosition, board)) {
            result.addAll(getMovablePosition(movePosition, direction, board));
        }
        return result;
    }
}

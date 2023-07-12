package softeer2nd.domain.chess.pieces;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    public Piece move(final Position targetPosition, final List<List<Piece>> board) {
        validationTargetPositionEqualCurrentPosition(targetPosition);

        return this.getMovablePosition(position, board).stream()
                .filter(position -> position.equals(targetPosition))
                .findFirst()
                .map(position -> new Pawn(this.color, position, this.directions))
                .orElseThrow(moveFailException());
    }

    @Override
    protected List<Position> getMovablePosition(final Position position, final List<List<Piece>> board) {
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
        if (isMoveEnemy(board, movePosition) || isMoveBlank(board, movePosition)) {
            result.add(movePosition);
        }
        return result;
    }

    private boolean isMoveEnemy(final List<List<Piece>> board, final Position movePosition) {
        return this.position.getX() != movePosition.getX() && isEnemy(this.color, movePosition, board);
    }

    private boolean isMoveBlank(final List<List<Piece>> board, final Position movePosition) {
        return this.position.getX() == movePosition.getX() && isBlank(movePosition, board);
    }
}

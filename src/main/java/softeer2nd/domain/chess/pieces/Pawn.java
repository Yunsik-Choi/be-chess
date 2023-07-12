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
    public Piece move(final Position targetPosition, final List<List<Piece>> board) {
        validationTargetPositionEqualCurrentPosition(targetPosition);

        return directions.stream()
                .filter(this.position::canMove)
                .map(this.position::move)
                .filter(movePosition -> movePosition.equals(targetPosition))
                .map(movePosition -> {
                    validationMoveAnotherPosition(targetPosition, board, movePosition);
                    return new Pawn(this.color, movePosition, this.directions);
                })
                .findFirst()
                .orElseThrow(() -> new RuntimeException("이동에 실패했습니다."));
    }

    private void validationTargetPositionEqualCurrentPosition(final Position targetPosition) {
        if (targetPosition.equals(this.position)) {
            throw new IllegalArgumentException("현재 위치와 같은 위치로 이동할 수 없습니다.");
        }
    }

    private void validationMoveAnotherPosition(
            final Position targetPosition,
            final List<List<Piece>> board,
            final Position movePosition
    ) {
        if (isSameColor(movePosition, board)) {
            throw new IllegalArgumentException("이동하려는 위치에 같은 편 기물이 존재합니다.");
        }
        if (this.position.getX() != targetPosition.getX() && !isEnemy(this.color, movePosition, board)) {
            throw new IllegalArgumentException("폰은 대각선에 다른 색상의 기물이 있어야만 움직일 수 있습니다.");
        }
    }
}

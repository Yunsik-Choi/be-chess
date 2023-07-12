package softeer2nd.domain.chess.pieces;

import java.util.ArrayList;
import java.util.List;

public class Blank extends Piece {
    private static final String REPRESENTATION = ".";
    private static final double POINT = 0.0;

    protected Blank(final Color color, final Position position, final List<Direction> directions) {
        super(POINT, REPRESENTATION, color, position, directions);
        validationCreateBlank(color, directions);
    }

    private static void validationCreateBlank(final Color color, final List<Direction> directions) {
        if (!color.equals(Color.NOCOLOR)) {
            throw new IllegalArgumentException("공백 색상이 아닙니다.");
        }
        if (!directions.isEmpty()) {
            throw new IllegalArgumentException("공백은 방향이 이동할 수 있는 방향이 없습니다.");
        }
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

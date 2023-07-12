package softeer2nd.domain.chess.pieces;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public abstract class Piece {
    public enum Color {
        WHITE, BLACK, NOCOLOR;

        public Optional<Color> getEnemy() {
            if (this.equals(WHITE)) {
                return Optional.of(BLACK);
            }
            if (this.equals(BLACK)) {
                return Optional.of(WHITE);
            }
            return Optional.empty();
        }
    }

    private final double point;
    private final String representation;

    protected final Color color;
    protected final Position position;
    protected final List<Direction> directions;

    protected Piece(
            final double point,
            final String representation,
            final Color color,
            final Position position,
            final List<Direction> directions
    ) {
        this.point = point;
        this.representation = representation;
        this.color = color;
        this.position = position;
        this.directions = directions;
    }

    public static Piece createBlank(final Position position) {
        return new Blank(Color.NOCOLOR, position, Direction.blankDirection());
    }

    public static Piece createWhitePawn(final Position position) {
        return new Pawn(Color.WHITE, position, Direction.whitePawnDirection());
    }

    public static Piece createBlackPawn(final Position position) {
        return new Pawn(Color.BLACK, position, Direction.blackPawnDirection());
    }

    public static Piece createWhiteKnight(final Position position) {
        return new Knight(Color.WHITE, position, Direction.knightDirection());
    }

    public static Piece createBlackKnight(final Position position) {
        return new Knight(Color.BLACK, position, Direction.knightDirection());
    }

    public static Piece createWhiteRook(final Position position) {
        return new Rook(Color.WHITE, position, Direction.linearDirection());
    }

    public static Piece createBlackRook(final Position position) {
        return new Rook(Color.BLACK, position, Direction.linearDirection());
    }

    public static Piece createWhiteBishop(final Position position) {
        return new Bishop(Color.WHITE, position, Direction.diagonalDirection());
    }

    public static Piece createBlackBishop(final Position position) {
        return new Bishop(Color.BLACK, position, Direction.diagonalDirection());
    }

    public static Piece createWhiteQueen(final Position position) {
        return new Queen(Color.WHITE, position, Direction.everyDirection());
    }

    public static Piece createBlackQueen(final Position position) {
        return new Queen(Color.BLACK, position, Direction.everyDirection());
    }

    public static Piece createWhiteKing(final Position position) {
        return new King(Color.WHITE, position, Direction.everyDirection());
    }

    public static Piece createBlackKing(final Position position) {
        return new King(Color.BLACK, position, Direction.everyDirection());
    }

    protected abstract void addMovablePosition(
            final Direction direction,
            final List<List<Piece>> board,
            final ArrayList<Position> result,
            final Position movePosition
    );

    public abstract Piece create(final Position position);

    public boolean isSamePosition(final Position targetPosition) {
        return this.position.equals(targetPosition);
    }

    public String getRepresentationPerPiece() {
        if (isBlack()) {
            return representation.toUpperCase();
        }
        return representation;
    }

    public double getPoint() {
        return this.point;
    }

    public Color getColor() {
        return this.color;
    }

    public Position getPosition() {
        return this.position;
    }

    public List<Direction> getDirections() {
        return this.directions;
    }

    public boolean isWhite() {
        return this.color.equals(Color.WHITE);
    }

    public boolean isBlack() {
        return this.color.equals(Color.BLACK);
    }

    public boolean isPawn() {
        return new Pawn(this.color, this.getPosition(), this.directions).equals(this);
    }

    public boolean isNoColor() {
        return this.getColor().equals(Color.NOCOLOR);
    }

    public boolean isSameColor(final Position position, final List<List<Piece>> board) {
        return board.get(position.getY()).get(position.getX()).color.equals(this.color);
    }

    public boolean isBlank(final Position position, final List<List<Piece>> board) {
        return board.get(position.getY()).get(position.getX()).color.equals(Color.NOCOLOR);
    }

    public boolean isEnemy(final Color color, final Position position, final List<List<Piece>> board) {
        Optional<Color> enemy = color.getEnemy();
        if (enemy.isEmpty()) {
            return false;
        }
        return board.get(position.getY()).get(position.getX()).color.equals(enemy.get());
    }

    public List<Position> getMovablePosition(
            final Position position,
            final Direction direction,
            final List<List<Piece>> board
    ) {
        ArrayList<Position> result = new ArrayList<>();
        if (!position.canMove(direction) || isSameColor(position.move(direction), board)) {
            return result;
        }
        Position movePosition = position.move(direction);
        addMovablePosition(direction, board, result, movePosition);
        return result;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Piece piece = (Piece) o;
        return color == piece.color && Objects.equals(position, piece.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, position);
    }
}

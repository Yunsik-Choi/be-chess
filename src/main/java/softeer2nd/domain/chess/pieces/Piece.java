package softeer2nd.domain.chess.pieces;

import static softeer2nd.domain.chess.pieces.Piece.Type.BISHOP;
import static softeer2nd.domain.chess.pieces.Piece.Type.KING;
import static softeer2nd.domain.chess.pieces.Piece.Type.KNIGHT;
import static softeer2nd.domain.chess.pieces.Piece.Type.NO_PIECE;
import static softeer2nd.domain.chess.pieces.Piece.Type.PAWN;
import static softeer2nd.domain.chess.pieces.Piece.Type.QUEEN;
import static softeer2nd.domain.chess.pieces.Piece.Type.ROOK;

import java.util.Objects;

public abstract class Piece {
    public enum Color {
        WHITE, BLACK, NOCOLOR

    }

    public enum Type {
        PAWN("p", 1.0),
        KNIGHT("n", 2.5),
        ROOK("r", 5.0),
        BISHOP("b", 3.0),
        QUEEN("q", 9.0),
        KING("k", 0.0),
        NO_PIECE(".", 0.0);

        public static final double DUPLICATE_FILE_PAWN_POINT = 0.5;

        private final String representation;

        private final double point;

        Type(final String representation, final double point) {
            this.representation = representation;
            this.point = point;
        }

        public String getWhiteRepresentation() {
            return representation;
        }

        public String getBlackRepresentation() {
            return representation.toUpperCase();
        }

        public double getPoint() {
            return point;
        }
    }

    protected final Color color;
    protected final Type type;
    protected final Position position;

    protected Piece(final Color color, final Type type, final Position position) {
        this.color = color;
        this.type = type;
        this.position = position;
    }

    public static Piece createBlank(final Position position) {
        return new Blank(Color.NOCOLOR, NO_PIECE, position);
    }

    public static Piece createWhitePawn(final Position position) {
        return new Pawn(Color.WHITE, PAWN, position);
    }

    public static Piece createBlackPawn(final Position position) {
        return new Pawn(Color.BLACK, PAWN, position);
    }

    public static Piece createWhiteKnight(final Position position) {
        return new Knight(Color.WHITE, KNIGHT, position);
    }

    public static Piece createBlackKnight(final Position position) {
        return new Knight(Color.BLACK, KNIGHT, position);
    }

    public static Piece createWhiteRook(final Position position) {
        return new Rook(Color.WHITE, ROOK, position);
    }

    public static Piece createBlackRook(final Position position) {
        return new Rook(Color.BLACK, ROOK, position);
    }

    public static Piece createWhiteBishop(final Position position) {
        return new Bishop(Color.WHITE, BISHOP, position);
    }

    public static Piece createBlackBishop(final Position position) {
        return new Bishop(Color.BLACK, BISHOP, position);
    }

    public static Piece createWhiteQueen(final Position position) {
        return new Queen(Color.WHITE, QUEEN, position);
    }

    public static Piece createBlackQueen(final Position position) {
        return new Queen(Color.BLACK, QUEEN, position);
    }

    public static Piece createWhiteKing(final Position position) {
        return new King(Color.WHITE, KING, position);
    }

    public static Piece createBlackKing(final Position position) {
        return new King(Color.BLACK, KING, position);
    }

    public abstract String getWhiteRepresentation();

    public abstract String getBlackRepresentation();

    public abstract double getPoint();

    public abstract Piece move(final Position position);

    public Color getColor() {
        return this.color;
    }

    public Type getType() {
        return this.type;
    }

    public Position getPosition() {
        return this.position;
    }

    public String getRepresentationPerPiece() {
        if (this.color.equals(Color.BLACK)) {
            return this.type.representation.toUpperCase();
        }
        return this.type.representation;
    }

    public boolean isWhite() {
        return this.color.equals(Color.WHITE);
    }

    public boolean isBlack() {
        return this.color.equals(Color.BLACK);
    }

    public boolean isPawn() {
        return this.type.equals(PAWN);
    }

    public boolean isNoPiece() {
        return this.type == NO_PIECE;
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
        return color == piece.color && type == piece.type && Objects.equals(position, piece.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, type, position);
    }
}

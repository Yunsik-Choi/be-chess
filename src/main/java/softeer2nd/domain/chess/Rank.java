package softeer2nd.domain.chess;

import static softeer2nd.domain.chess.ChessGame.WIDTH;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import softeer2nd.domain.chess.pieces.Piece;
import softeer2nd.domain.chess.pieces.Position;

public class Rank {
    private final List<Piece> pieces = new ArrayList<>();

    public Rank() {
        IntStream.range(0, WIDTH)
                .forEach(i -> pieces.add(null));
    }

    public static Rank createNoPiece(final int y) {
        Rank rank = new Rank();
        IntStream.range(0, WIDTH).forEach(x -> rank.set(x, Piece.createBlank(new Position(x, y))));
        return rank;
    }

    public static Rank createBlackGeneral(final int y) {
        Rank rank = new Rank();
        rank.pieces.set(0, Piece.createBlackRook(new Position(0, y)));
        rank.pieces.set(1, Piece.createBlackKnight(new Position(1, y)));
        rank.pieces.set(2, Piece.createBlackBishop(new Position(2, y)));
        rank.pieces.set(3, Piece.createBlackQueen(new Position(3, y)));
        rank.pieces.set(4, Piece.createBlackKing(new Position(4, y)));
        rank.pieces.set(5, Piece.createBlackBishop(new Position(5, y)));
        rank.pieces.set(6, Piece.createBlackKnight(new Position(6, y)));
        rank.pieces.set(7, Piece.createBlackRook(new Position(7, y)));
        return rank;
    }

    public static Rank createWhiteGeneral(final int y) {
        Rank rank = new Rank();
        rank.pieces.set(0, Piece.createWhiteRook(new Position(0, y)));
        rank.pieces.set(1, Piece.createWhiteKnight(new Position(1, y)));
        rank.pieces.set(2, Piece.createWhiteBishop(new Position(2, y)));
        rank.pieces.set(3, Piece.createWhiteQueen(new Position(3, y)));
        rank.pieces.set(4, Piece.createWhiteKing(new Position(4, y)));
        rank.pieces.set(5, Piece.createWhiteBishop(new Position(5, y)));
        rank.pieces.set(6, Piece.createWhiteKnight(new Position(6, y)));
        rank.pieces.set(7, Piece.createWhiteRook(new Position(7, y)));
        return rank;
    }

    public static Rank createBlackPawn(final int y) {
        Rank rank = new Rank();
        for (int x = 0; x < WIDTH; x++) {
            rank.pieces.set(x, Piece.createBlackPawn(new Position(x, y)));
        }
        return rank;
    }

    public static Rank createWhitePawn(final int y) {
        Rank rank = new Rank();
        for (int x = 0; x < WIDTH; x++) {
            rank.pieces.set(x, Piece.createWhitePawn(new Position(x, y)));
        }
        return rank;
    }

    public List<Piece> getPieces() {
        return this.pieces;
    }

    public void set(final int index, final Piece piece) {
        if (isIndexOutOfBound(index)) {
            throw new IndexOutOfBoundsException("행의 범위를 벗어났습니다.");
        }
        pieces.set(index, piece);
    }

    private static boolean isIndexOutOfBound(final int index) {
        return index < 0 || index >= WIDTH;
    }

    public Piece getPiece(final int index) {
        return this.pieces.get(index);
    }
}

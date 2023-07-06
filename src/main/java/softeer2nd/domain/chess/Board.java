package softeer2nd.domain.chess;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import softeer2nd.common.util.StringUtils;
import softeer2nd.domain.chess.pieces.Piece;
import softeer2nd.domain.chess.pieces.Piece.Color;
import softeer2nd.domain.chess.pieces.Piece.Type;
import softeer2nd.domain.chess.pieces.Position;

public class Board {
    public static final int HEIGHT_SIZE = 8;
    private static final int WHITE_PAWN_LINE_Y = 6;
    private static final int WHITE_GENERAL_LINE_Y = 7;
    private static final int BLACK_PAWN_LINE_Y = 1;
    private static final int BLACK_GENERAL_LINE_Y = 0;

    private final List<Rank> ranks = new ArrayList<>();

    public void initializeEmpty() {
        ranks.clear();
        IntStream.range(0, Board.HEIGHT_SIZE).forEach(y -> ranks.add(y, Rank.createNoPiece()));
    }

    public void initialize() {
        ranks.clear();
        IntStream.range(0, HEIGHT_SIZE).forEach(y -> ranks.add(Rank.createNoPiece()));

        setPiece(
                ranks.get(BLACK_GENERAL_LINE_Y),
                Piece.createBlackRook(),
                Piece.createBlackKnight(),
                Piece.createBlackBishop(),
                Piece.createBlackQueen(),
                Piece.createBlackKing(),
                Piece.createBlackBishop(),
                Piece.createBlackKnight(),
                Piece.createBlackRook()
        );

        setPiece(
                ranks.get(WHITE_GENERAL_LINE_Y),
                Piece.createWhiteRook(),
                Piece.createWhiteKnight(),
                Piece.createWhiteBishop(),
                Piece.createWhiteQueen(),
                Piece.createWhiteKing(),
                Piece.createWhiteBishop(),
                Piece.createWhiteKnight(),
                Piece.createWhiteRook()
        );

        setPiece(ranks.get(WHITE_PAWN_LINE_Y), Piece.createWhitePawn());
        setPiece(ranks.get(BLACK_PAWN_LINE_Y), Piece.createBlackPawn());
    }

    private void setPiece(final Rank rank, final Piece... pieces) {
        IntStream.range(0, pieces.length)
                .forEach(i -> rank.set(i, pieces[i]));
    }

    private void setPiece(
            final Rank rank,
            final Piece piece
    ) {
        IntStream.range(0, Rank.WIDTH)
                .forEach(x -> rank.set(x, piece));
    }

    public String show() {
        return boardRepresentationFormatting(boardRepresentation());
    }

    private List<List<String>> boardRepresentation() {
        List<List<String>> result = IntStream.range(0, HEIGHT_SIZE)
                .mapToObj(y -> new ArrayList<String>())
                .collect(Collectors.toList());

        IntStream.range(0, HEIGHT_SIZE)
                .forEach(y -> setLineRepresentation(result, y));

        return result;
    }

    private void setLineRepresentation(final List<List<String>> result, final int y) {
        Rank rank = ranks.get(y);

        IntStream.range(0, Rank.WIDTH)
                .forEach(x -> result.get(y).add(x, getPieceRepresentation(rank.getPieces().get(x))));
    }

    private String getPieceRepresentation(final Piece piece) {
        return piece.getRepresentationPerPiece();
    }

    private String boardRepresentationFormatting(final List<List<String>> result) {
        return result.stream()
                .map(list -> String.join("", list))
                .map(StringUtils::appendNewLine)
                .collect(Collectors.joining());
    }

    public int pieceCount() {
        return this.ranks.stream()
                .mapToInt(rank -> Long.valueOf(rank.getPieces().stream().filter(Piece::isNoPiece).count()).intValue())
                .sum();
    }

    public int pieceCount(final Color color, final Type type) {
        return this.ranks.stream()
                .mapToInt(rank -> rank.pieceCount(color, type))
                .sum();
    }

    public Piece findPiece(final String position) {
        Position findPiecePosition = new Position(position);

        return this.ranks.get(findPiecePosition.getY()).getPiece(findPiecePosition.getX());
    }

    public void move(final String position, final Piece piece) {
        Position movePosition = new Position(position);

        this.ranks.get(movePosition.getY()).set(movePosition.getX(), piece);
    }

    public double calculatePoint(final Color color) {
        double totalPoint = this.ranks.stream()
                .flatMap(rank -> rank.getPieces().stream())
                .filter(piece -> piece.getColor().equals(color))
                .mapToDouble(piece -> piece.getType().getPoint())
                .sum();

        return calculateFileHasPawnOfPoint(totalPoint);
    }

    private double calculateFileHasPawnOfPoint(double point) {
        double calculateTotalDuplicatePawnDeduction = IntStream.range(0, Board.HEIGHT_SIZE)
                .map(this::countFileHasPawn)
                .filter(Board::isFileHasManyPawn)
                .mapToDouble(count -> count * Type.DUPLICATE_FILE_PAWN_POINT)
                .sum();

        return point - calculateTotalDuplicatePawnDeduction;
    }

    private static boolean isFileHasManyPawn(final int count) {
        return count > 1;
    }

    private int countFileHasPawn(int x) {
        long countOfRankHasPawn = IntStream.range(0, HEIGHT_SIZE)
                .filter(y -> ranks.get(y).getPieces().get(x).isPawn())
                .count();

        return Long.valueOf(countOfRankHasPawn).intValue();
    }

    public List<Piece> sortByPoint(final Color color) {
        return this.ranks.stream()
                .flatMap(rank -> rank.getPieces().stream())
                .filter(piece -> piece.getColor().equals(color))
                .sorted(sortPieceByPointComparator())
                .collect(Collectors.toList());
    }

    private static Comparator<Piece> sortPieceByPointComparator() {
        return (o1, o2) -> (int) (o2.getType().getPoint() - o1.getType().getPoint());
    }
}

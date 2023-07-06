package softeer2nd.domain.chess;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import softeer2nd.common.util.StringUtils;
import softeer2nd.domain.chess.pieces.Piece;

public class Board {
    public static final int HEIGHT_SIZE = 8;
    private static final int WHITE_PAWN_LINE_Y = 6;
    private static final int WHITE_GENERAL_LINE_Y = 7;
    private static final int BLACK_PAWN_LINE_Y = 1;
    private static final int BLACK_GENERAL_LINE_Y = 0;

    private final List<Line> lines = new ArrayList<>();

    public void initialize() {
        lines.clear();
        IntStream.range(0, HEIGHT_SIZE).forEach(y -> lines.add(Line.createNoPiece()));

        setPiece(
                lines.get(BLACK_GENERAL_LINE_Y),
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
                lines.get(WHITE_GENERAL_LINE_Y),
                Piece.createWhiteRook(),
                Piece.createWhiteKnight(),
                Piece.createWhiteBishop(),
                Piece.createWhiteQueen(),
                Piece.createWhiteKing(),
                Piece.createWhiteBishop(),
                Piece.createWhiteKnight(),
                Piece.createWhiteRook()
        );

        setPiece(lines.get(WHITE_PAWN_LINE_Y), Piece.createWhitePawn());
        setPiece(lines.get(BLACK_PAWN_LINE_Y), Piece.createBlackPawn());
    }

    private void setPiece(final Line line, final Piece... pieces) {
        IntStream.range(0, pieces.length)
                .forEach(i -> line.set(i, pieces[i]));
    }

    private void setPiece(
            final Line line,
            final Piece piece
    ) {
        IntStream.range(0, Line.WIDTH)
                .forEach(x -> line.set(x, piece));
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
        Line line = lines.get(y);

        IntStream.range(0, Line.WIDTH)
                .forEach(x -> result.get(y).add(x, getPieceRepresentation(line.getPieces().get(x))));
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
        return this.lines.stream()
                .mapToInt(line -> Long.valueOf(line.getPieces().stream().filter(Piece::isNoPiece).count()).intValue())
                .sum();
    }
}

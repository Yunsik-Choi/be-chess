package softeer2nd.domain.chess;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
    private static final String BOARD_EMPTY_REPRESENTATION = ".";

    private final List<Line> lines = new ArrayList<>();

    public void initialize() {
        lines.clear();
        IntStream.range(0, HEIGHT_SIZE).forEach(y -> lines.add(new Line()));

        initializePiece(
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

        initializePiece(
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

        initializePiece(lines.get(WHITE_PAWN_LINE_Y), Piece.createWhitePawn());
        initializePiece(lines.get(BLACK_PAWN_LINE_Y), Piece.createBlackPawn());
    }

    private void initializePiece(final Line line, final Piece... pieces) {
        for (int i = 0; i < Line.WIDTH; i++) {
            if (i > pieces.length) {
                line.set(i, null);
            }
            line.set(i, pieces[i]);
        }
    }

    private void initializePiece(
            final Line line,
            final Piece piece
    ) {
        IntStream.range(0, Line.WIDTH)
                .forEach(x -> line.set(x, piece));
    }

    public String show() {
        List<List<String>> result = initializeBoardRepresentation();

        setBoardRepresentation(result);

        return boardRepresentationFormatting(result);
    }

    private List<List<String>> initializeBoardRepresentation() {
        return IntStream.range(0, Board.HEIGHT_SIZE)
                .mapToObj(y -> IntStream.range(0, Line.WIDTH)
                        .mapToObj(x -> BOARD_EMPTY_REPRESENTATION).collect(Collectors.toList())
                ).collect(Collectors.toUnmodifiableList());
    }

    private void setBoardRepresentation(final List<List<String>> result) {
        IntStream.range(0, HEIGHT_SIZE)
                .forEach(y -> setLineRepresentation(result, y));
    }

    private void setLineRepresentation(final List<List<String>> result, final int y) {
        Line line = lines.get(y);

        IntStream.range(0, Line.WIDTH)
                .forEach(x -> result.get(y).set(x, getPieceRepresentation(line.getPieces().get(x))));
    }

    private String getPieceRepresentation(final Piece piece) {
        if (piece == null) {
            return BOARD_EMPTY_REPRESENTATION;
        }
        return piece.getRepresentation();
    }

    private String boardRepresentationFormatting(final List<List<String>> result) {
        return result.stream()
                .map(list -> String.join("", list))
                .map(StringUtils::appendNewLine)
                .collect(Collectors.joining());
    }

    public int pieceCount() {
        return this.lines.stream()
                .mapToInt(line -> Long.valueOf(line.getPieces().stream().filter(Objects::nonNull).count()).intValue())
                .sum();
    }
}

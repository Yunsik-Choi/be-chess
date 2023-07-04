package softeer2nd.domain.chess;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import softeer2nd.domain.chess.pieces.Pawn;
import softeer2nd.domain.chess.pieces.Point;

public class Board {
    private static final int WIDTH_SIZE = 8;
    private static final int WHITE_PAWN_Y = 1;
    private static final int BLACK_PAWN_Y = 6;

    private final List<Pawn> pawns = new ArrayList<>();

    public void initialize() {
        initializePawn(Pawn.WHITE_COLOR, Pawn.WHITE_REPRESENTATION, WHITE_PAWN_Y);
        initializePawn(Pawn.BLACK_COLOR, Pawn.BLACK_REPRESENTATION, BLACK_PAWN_Y);
    }

    private void initializePawn(final String color, final String representation, final int y) {
        IntStream.range(0, WIDTH_SIZE)
                .forEach(x -> pawns.add(new Pawn(color, representation, new Point(x, y))));
    }

    public void add(final Pawn pawn) {
        pawns.add(pawn);
    }

    public Pawn findPawn(final int index) {
        if (pawns.size() <= index) {
            throw new IndexOutOfBoundsException("체스 판에 추가된 기물의 수보다 큽니다.");
        }
        return pawns.get(index);
    }

    public int size() {
        return pawns.size();
    }

    public String getWhitePawnsResult() {
        return getPawnsResultOwnedBy(Pawn.WHITE_REPRESENTATION);
    }

    public String getBlackPawnsResult() {
        return getPawnsResultOwnedBy(Pawn.BLACK_REPRESENTATION);
    }

    private String getPawnsResultOwnedBy(final String ownedRepresentation) {
        return pawns.stream()
                .map(Pawn::getRepresentation)
                .filter(representation -> representation.equals(ownedRepresentation))
                .collect(Collectors.joining());
    }
}

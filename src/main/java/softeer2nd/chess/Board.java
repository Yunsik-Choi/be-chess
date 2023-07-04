package softeer2nd.chess;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import softeer2nd.chess.pieces.Pawn;

public class Board {
    private static final int WIDTH_SIZE = 8;

    private final List<Pawn> pawns = new ArrayList<>();

    public void initialize() {
        initializePawn(Pawn.WHITE_COLOR, Pawn.WHITE_REPRESENTATION);
        initializePawn(Pawn.BLACK_COLOR, Pawn.BLACK_REPRESENTATION);
    }

    private void initializePawn(final String color, final String representation) {
        IntStream.range(0, WIDTH_SIZE)
                .forEach(i -> pawns.add(new Pawn(color, representation)));
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

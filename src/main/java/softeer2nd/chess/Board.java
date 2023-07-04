package softeer2nd.chess;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import softeer2nd.chess.pieces.Pawn;

public class Board {
    private final List<Pawn> pawns = new ArrayList<>();

    public void initialize() {

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
        return pawns.stream()
                .map(Pawn::getRepresentation)
                .filter(representation -> representation.equals(Pawn.WHITE_REPRESENTATION))
                .collect(Collectors.joining());
    }
}

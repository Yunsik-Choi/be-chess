package softeer2nd.chess;

import java.util.ArrayList;
import java.util.List;
import softeer2nd.chess.pieces.Pawn;

public class Board {
    private final List<Pawn> pawns = new ArrayList<>();

    public void add(final Pawn pawn) {
        pawns.add(pawn);
    }

    public Pawn findPawn(final int index) {
        return pawns.get(index);
    }

    public int size() {
        return pawns.size();
    }
}

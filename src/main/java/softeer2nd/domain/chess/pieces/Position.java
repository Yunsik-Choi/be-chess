package softeer2nd.domain.chess.pieces;

import softeer2nd.domain.chess.Board;
import softeer2nd.domain.chess.Rank;

public class Position {
    private static final int POSITION_LENGTH = 2;

    private final int x;
    private final int y;

    public Position(final String position) {
        if (position.length() != POSITION_LENGTH) {
            throw new IllegalArgumentException("위치 정보가 잘못됐습니다.");
        }
        int xPos = lowerCaseToInt(position);
        int yPos = convertMathCode(position);
        validationPositionOutOfBound(xPos, yPos);
        this.x = xPos;
        this.y = yPos;
    }

    private int lowerCaseToInt(final String position) {
        return position.charAt(0) - 'a';
    }

    private int convertMathCode(final String position) {
        return Board.HEIGHT_SIZE - Character.getNumericValue(position.charAt(1));
    }

    private void validationPositionOutOfBound(final int xPos, final int yPos) {
        if (xPos < 0 || xPos >= Rank.WIDTH || yPos < 0 || yPos >= Board.HEIGHT_SIZE) {
            throw new IllegalArgumentException("위치 정보가 체스판의 크기를 벗어났습니다.");
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}

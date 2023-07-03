package softeer2nd.domain;

import java.util.Objects;

public class Pawn {
    private final String color;

    public Pawn(final String color) {
        this.color = color;
    }

    public String getColor() {
        return this.color;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Pawn pawn = (Pawn) o;
        return Objects.equals(color, pawn.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(color);
    }
}

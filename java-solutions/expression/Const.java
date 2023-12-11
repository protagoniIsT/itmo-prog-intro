package expression;

import java.util.Objects;

public class Const implements BasicExpressionInterface {
    private final int value;

    public Const(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public int evaluate(int value) {
        return this.value;
    }

    @Override
    public int evaluate(int value1, int value2, int value3) {
        return this.value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof final Const constant) {
            return Objects.equals(value, constant.value);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value) * 300;
    }
}
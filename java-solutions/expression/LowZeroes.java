package expression;

import java.util.Objects;

public class LowZeroes implements BasicExpressionInterface {
    public final TripleExpression expression;

    public LowZeroes(TripleExpression expression) {
        this.expression = expression;
    }

    @Override
    public int evaluate(int value) {
        return 32 - Integer.toBinaryString(value).length();
    }

    @Override
    public int evaluate(int x, int y, int z) {
        int result = expression.evaluate(x, y, z);
        return 32 - Integer.toBinaryString(result).length();
    }

    @Override
    public String toString() {
        StringBuilder exp = new StringBuilder();
        exp.append("l0(").append(expression.toString()).append(")");
        return exp.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof final LowZeroes l) {
            return Objects.equals(expression, l.expression);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(expression) * 300;
    }
}
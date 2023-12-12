package expression;

import java.util.Objects;

public class Negate implements BasicExpressionInterface {
    public final TripleExpression expression;

    public Negate(TripleExpression expression) {
        this.expression = expression;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        int result = expression.evaluate(x, y, z);
        return -result;
    }

    @Override
    public String toString() {
        return "-(" + expression.toString() + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof final Negate negative) {
            return Objects.equals(expression, negative.expression);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(expression) * 300;
    }

    @Override
    public int evaluate(int x) {
        return  -x;
    }
}
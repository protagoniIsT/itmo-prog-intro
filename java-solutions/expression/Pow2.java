package expression;

import java.util.Objects;

public class Pow2 implements BasicExpressionInterface {
    public final TripleExpression expression;

    public Pow2(TripleExpression expression) {
        this.expression = expression;
    }

    @Override
    public int evaluate(int value) {
        return (int) Math.pow(2, value);
    }

    @Override
    public int evaluate(int x, int y, int z) {
        int result = expression.evaluate(x, y, z);
        return (int) Math.pow(2, result);
    }

    @Override
    public String toString() {
        return "pow2(" + expression.toString() + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof final Pow2 pow) {
            return Objects.equals(expression, pow.expression);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(expression) * 300;
    }
}
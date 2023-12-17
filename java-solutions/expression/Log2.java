package expression;

import java.util.Objects;

public class Log2 implements BasicExpressionInterface {
    public final TripleExpression expression;

    public Log2(TripleExpression expression) {
        this.expression = expression;
    }

    protected int calcLog(int v) {
        int log = 0;
        while (Math.pow(2, log + 1) <= v) {
            log++;
        }
        return log;
    }

    @Override
    public int evaluate(int value) {
        return calcLog(value);
    }

    @Override
    public int evaluate(int x, int y, int z) {
        int result = expression.evaluate(x, y, z);
        return calcLog(result);
    }

    @Override
    public String toString() {
        return "log2(" + expression.toString() + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof final Log2 log) {
            return Objects.equals(expression, log.expression);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(expression) * 300;
    }
}
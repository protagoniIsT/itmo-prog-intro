package expression;

import java.util.Objects;

public class HighZeroes implements BasicExpressionInterface {
    public final TripleExpression expression;

    public HighZeroes(TripleExpression expression) {
        this.expression = expression;
    }

    @Override
    public int evaluate(int value) {
        String bin = Integer.toBinaryString(value);
        int cnt = 0;
        int i = bin.length() - 1;
        while (bin.charAt(i) != '1') {
            cnt++;
            i--;
        }
        return cnt;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        int result = expression.evaluate(x, y, z);
        String bin = Integer.toBinaryString(result);
        int cnt = 0;
        int i = bin.length() - 1;
        while (bin.charAt(i) != '1') {
            cnt++;
            i--;
        }
        return cnt;
    }

    @Override
    public String toString() {
        StringBuilder exp = new StringBuilder();
        exp.append("t0(").append(expression.toString()).append(")");
        return exp.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof final HighZeroes h) {
            return Objects.equals(expression, h.expression);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(expression) * 300;
    }
}
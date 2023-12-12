package expression.exceptions;

import expression.BasicExpressionInterface;
import expression.Negate;

import java.util.Objects;

public class CheckedNegate extends Negate {
    private final BasicExpressionInterface expression;

    public CheckedNegate(BasicExpressionInterface expression) {
        super(expression);
        this.expression = expression;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        int result = expression.evaluate(x, y, z);
        if(result == Integer.MIN_VALUE){
            throw new RuntimeException("Cannot negate value" + result);
        }
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
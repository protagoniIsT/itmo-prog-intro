package expression.exceptions;

import expression.Pow2;
import expression.TripleExpression;
import expression.exceptions.CheckedDivide;

import java.util.Objects;

public class CheckedPow2 extends Pow2 {
    public CheckedPow2(TripleExpression expression) {
        super(expression);
    }

    @Override
    public int evaluate(int value) {
        if (value >= 31  || value < 0) {
            throw new ArithmeticException("Overflow");
        }
        return (int) Math.pow(2, value);
    }

    @Override
    public int evaluate(int x, int y, int z) {
        int result = expression.evaluate(x, y, z);
        return evaluate(result);
    }
}
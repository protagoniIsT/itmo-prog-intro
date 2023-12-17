package expression.exceptions;
import expression.*;

import java.util.Objects;

public class CheckedLog2 extends Log2 {
    public CheckedLog2(TripleExpression expression) {
        super(expression);
    }

    @Override
    public int evaluate(int value) {
        if (value <= 0) {
            throw new IllegalArgumentException("Cannot logarithm value " + value);
        }
        return calcLog(value);
    }

    @Override
    public int evaluate(int x, int y, int z) {
        int result = expression.evaluate(x, y, z);
        return evaluate(result);
    }
}
package expression.exceptions;
import expression.Add;
import expression.BasicExpressionInterface;

public class CheckedAdd extends Add {
    public CheckedAdd(BasicExpressionInterface firstOperand, BasicExpressionInterface secondOperand) {
        super(firstOperand, secondOperand);
    }

    @Override
    public int calculate(int firstOperand, int secondOperand) {
        long result = (long) firstOperand + (long) secondOperand;
        if (result > Integer.MAX_VALUE || result < Integer.MIN_VALUE) {
            throw new ArithmeticException("Overflow");
        }
        return (int) result;
    }
}
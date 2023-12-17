package expression.exceptions;


import expression.BasicExpressionInterface;
import expression.Divide;

public class CheckedDivide extends Divide {
    public CheckedDivide(BasicExpressionInterface firstOperand, BasicExpressionInterface secondOperand) {
        super(firstOperand, secondOperand);
    }

    @Override
    protected int calculate(int firstOperand, int secondOperand) {
        if (secondOperand == 0) {
            throw new ArithmeticException("Division by zero");
        }
        if (firstOperand == Integer.MIN_VALUE && secondOperand == -1) {
            throw new ArithmeticException("Overflow");
        }
        return firstOperand / secondOperand;
    }
}
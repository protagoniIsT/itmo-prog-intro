package expression;

public class Subtract extends AbstractBinaryOperation {

    public Subtract(BasicExpressionInterface firstOperand, BasicExpressionInterface secondOperand) {
        super(firstOperand, secondOperand);
    }

    @Override
    protected int calculate(int firstOperand, int secondOperand) {
        return firstOperand - secondOperand;
    }

    @Override
    protected String getOperator() {
        return " - ";
    }
}
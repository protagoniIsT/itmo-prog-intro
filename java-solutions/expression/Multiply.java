package expression;

public class Multiply extends AbstractBinaryOperation {

    public Multiply(BasicExpressionInterface firstOperand, BasicExpressionInterface secondOperand) {
        super(firstOperand, secondOperand);
    }

    @Override
    protected int calculate(int firstOperand, int secondOperand) {
        return firstOperand * secondOperand;
    }

    @Override
    protected String getOperator() {
        return " * ";
    }
}
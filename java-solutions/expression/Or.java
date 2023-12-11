package expression;

public class Or extends AbstractBinaryOperation {
    public Or(BasicExpressionInterface firstOperand, BasicExpressionInterface secondOperand) {
        super(firstOperand, secondOperand);
    }

    @Override
    protected int calculate(int firstOperand, int secondOperand) {
        return firstOperand | secondOperand;
    }

    @Override
    protected String getOperator() {
        return " | ";
    }
}

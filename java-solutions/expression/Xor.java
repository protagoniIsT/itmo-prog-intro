package expression;

public class Xor extends AbstractBinaryOperation {
    public Xor(BasicExpressionInterface firstOperand, BasicExpressionInterface secondOperand) {
        super(firstOperand, secondOperand);
    }

    @Override
    protected int calculate(int firstOperand, int secondOperand) {
        return firstOperand ^ secondOperand;
    }

    @Override
    protected String getOperator() {
        return " ^ ";
    }

}

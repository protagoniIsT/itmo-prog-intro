package expression;

public class Add extends AbstractBinaryOperation {
    public Add(BasicExpressionInterface firstOperand, BasicExpressionInterface secondOperand) {
        super(firstOperand, secondOperand);
    }

    @Override
    public int calculate(int first, int second) {
        return first + second;
    }

    @Override
    public String getOperator() {
        return " + ";
    }
}
package expression;

import java.util.Objects;

public class Variable implements BasicExpressionInterface {
    private final String variable;

    public Variable(String variable) {
        this.variable = variable;
    }

    @Override
    public int evaluate(int value) {
        return value;
    }

    @Override
    public int evaluate(int value1, int value2, int value3) {
        if (variable.equals("x")) {
            return value1;
        } else if (variable.equals("y")) {
            return value2;
        }
        return value3;
    }

    @Override
    public String toString() {
        return variable;
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof final Variable var) {
            return variable.equals(var.variable);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(variable) * 300;
    }
}
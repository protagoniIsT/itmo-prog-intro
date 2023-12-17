package expression.parser;

import expression.*;

public class ExpressionParser implements TripleParser {
    private String expression;
    private int index;

    public TripleExpression parse(String expression) {
        this.expression = expression;
        this.index = 0;
        return parseExpression();
    }

    private TripleExpression parseExpression() {
        TripleExpression result = parseBitXor();
        while (index < expression.length()) {
            char operation = peek();
            if (operation == '|') {
                movePointer();
                result = createBinaryOperation(result, parseBitXor(), operation);
            } else {
                break;
            }
        }
        return result;
    }

    private TripleExpression parseBitXor() {
        TripleExpression result = parseBitAnd();
        while (index < expression.length()) {
            char operation = peek();
            if (operation == '^') {
                movePointer();
                result = createBinaryOperation(result, parseBitAnd(), operation);
            } else {
                break;
            }
        }
        return result;
    }

    private TripleExpression parseBitAnd() {
        TripleExpression result = parseAdditive();
        while (index < expression.length()) {
            char operation = peek();
            if (operation == '&') {
                movePointer();
                result = createBinaryOperation(result, parseAdditive(), operation);
            } else {
                break;
            }
        }
        return result;
    }

    private TripleExpression parseAdditive() {
        TripleExpression result = parseTerm();
        while (index < expression.length()) {
            char operation = peek();
            if (operation == '+' || operation == '-') {
                movePointer();
                result = createBinaryOperation(result, parseTerm(), operation);
            } else {
                break;
            }
        }
        return result;
    }

    private TripleExpression parseTerm() {
        TripleExpression result = parseFactor();
        while (index < expression.length()) {
            char operation = peek();
            if (operation == '*' || operation == '/') {
                movePointer();
                result = createBinaryOperation(result, parseFactor(), operation);
            } else {
                break;
            }
        }
        return result;
    }

    private TripleExpression parseFactor() {
        skipWhitespace();
        char currentChar = peek();
        if (currentChar == '(') {
            movePointer();
            TripleExpression result = parseExpression();
            expect();
            return result;
        } else if (currentChar == '-') {
            movePointer();
            return parseNegativeExpression();
        } else if (currentChar == '~') {
            movePointer();
            return parseNotExpression();
        } else if (Character.isDigit(currentChar)) {
            return new Const(parseNumber(false));
        } else if (Character.isLetter(currentChar)) {
            return new Variable(parseVariable());
        } else {
            throw new RuntimeException("Unexpected character: " + currentChar);
        }
    }

    private TripleExpression parseNegativeExpression() {
        skipWhitespace();
        int minusCount = 1;
        while (peek() == '-') {
            minusCount++;
            movePointer();
            skipWhitespace();
        }
        TripleExpression result;
        boolean isNegative = minusCount % 2 != 0;
        if (peek() == '(') {
            movePointer();
            result = parseExpression();
            expect();
            if (isNegative) {
                result = createUnaryOperation(result, '-');
            }
        } else if (Character.isDigit(peek())) {
            if (minusCount == 2 && peekNext() == '2') {
                result = new Const(parseNumber(false));
            } else {
                result = new Const(parseNumber(isNegative));
            }
        } else {
            result = parseFactor();
            if (isNegative) {
                result = createUnaryOperation(result, '-');
            }
        }
        return result;
    }

    private TripleExpression parseNotExpression() {
        skipWhitespace();
        TripleExpression result;
        if (peek() == '(') {
            movePointer();
            result = createUnaryOperation(parseExpression(), '~');
            expect();
        } else {
            result = createUnaryOperation(parseFactor(), '~');
        }
        return result;
    }

    private int parseNumber(boolean isNegative) {
        StringBuilder number = new StringBuilder();
        while (Character.isDigit(peek())) {
            number.append(expression.charAt(index++));
        }
        String numberStr = number.toString();
        try {
            long result = Long.parseLong(numberStr);
            if (isNegative) {
                result = -result;
            }
            return (int) result;
        } catch (NumberFormatException e) {
            throw new RuntimeException("Number format exception for string: " + numberStr, e);
        }
    }

    private String parseVariable() {
        StringBuilder variable = new StringBuilder();
        while (Character.isLetter(peek())) {
            variable.append(expression.charAt(index++));
        }
        return variable.toString();
    }

    private static boolean isLineSeparator(char c) {
        return (c == '\n') || (c == '\t') || (c == '\r');
    }

    private TripleExpression createBinaryOperation(TripleExpression leftOp, TripleExpression rightOp, char operation) {
        return switch (operation) {
            case '+' -> new Add((BasicExpressionInterface) leftOp, (BasicExpressionInterface) rightOp);
            case '-' -> new Subtract((BasicExpressionInterface) leftOp, (BasicExpressionInterface) rightOp);
            case '*' -> new Multiply((BasicExpressionInterface) leftOp, (BasicExpressionInterface) rightOp);
            case '/' -> new Divide((BasicExpressionInterface) leftOp, (BasicExpressionInterface) rightOp);
            case '^' -> new Xor((BasicExpressionInterface) leftOp, (BasicExpressionInterface) rightOp);
            case '|' -> new Or((BasicExpressionInterface) leftOp, (BasicExpressionInterface) rightOp);
            case '&' -> new And((BasicExpressionInterface) leftOp, (BasicExpressionInterface) rightOp);
            default -> throw new RuntimeException("Unexpected binary operator: " + operation);
        };
    }

    private TripleExpression createUnaryOperation(TripleExpression operand, char operation) {
        return switch (operation) {
            case '-' -> new Negate(operand);
            case '~' -> new Not(operand);
            default -> throw new RuntimeException("Unexpected unary operator: " + operation);
        };
    }

    private void movePointer() {
        if (index < expression.length()) {
            index++;
        }
    }

    private void expect() {
        if (peek() != ')') {
            throw new RuntimeException("Expected '" + ')' + "' but found '" + peek() + "'");
        }
        movePointer();
    }

    private char peek() {
        skipWhitespace();
        if (index >= expression.length()) return '\0';
        return expression.charAt(index);
    }

    private char peekNext() {
        if (index + 1 >= expression.length()) {
            return '\0';
        }
        return expression.charAt(index + 1);
    }

    private void skipWhitespace() {
        while (index < expression.length() && isWhitespaceOrInvisible(expression.charAt(index))) {
            index++;
        }
    }

    private boolean isWhitespaceOrInvisible(char c) {
        return Character.isWhitespace(c) || isLineSeparator(c);
    }
}
package expression.exceptions;

import expression.*;
import expression.parser.TripleParser;

public class ExpressionParser implements TripleParser {
    private String expression;
    private int index;

    public TripleExpression parse(String expression) {
        this.expression = expression;
        this.index = 0;
        validateExpression();
        return parseExpression();
    }

    private TripleExpression parseExpression() {
        isCorrectBracketSequence();
        TripleExpression result = parseTerm();
        while (true) {
            if (match('+')) {
                result = createBinaryOperation(result, parseTerm(), '+');
            } else if (match('-')) {
                result = createBinaryOperation(result, parseTerm(), '-');
            } else {
                break;
            }
        }
        return result;
    }

    private TripleExpression parseTerm() {
        TripleExpression result = parseFactor();
        while (true) {
            if (match('*')) {
                result = createBinaryOperation(result, parseFactor(), '*');
            } else if (match('/')) {
                result = createBinaryOperation(result, parseFactor(), '/');
            } else {
                break;
            }
        }
        return result;
    }

    private TripleExpression parseFactor() {
        skipWhitespace();
        if (match('(')) {
            TripleExpression result = parseExpression();
            expect(')');
            return result;
        } else if (match('-')) {
            if (Character.isDigit(peek())) {
                return new Const(parseNumber(true));
            }
            return new CheckedNegate((BasicExpressionInterface) parseFactor());
        } else if (Character.isDigit(peek())) {
            TripleExpression result = new Const(parseNumber(false));
            checkNextSymbolAfterVarOrNumber();
            return result;
        } else if (isVariable(peek())) {
            TripleExpression result = new Variable(parseVariable());
            checkNextSymbolAfterVarOrNumber();
            return result;
        } else if (isUnaryOperationPart(peek())) {
            String operation = parseUnaryOperator();
            if (operation.equals("pow2") || operation.equals("log2")) {
                if (peek() != '(') {
                    skipWhitespace();
                    if (Character.isLetterOrDigit(peek()) && !Character.isWhitespace(expression.charAt(index - 1))) {
                        throw new RuntimeException("Unary operation '" + operation + "' must be followed by a whitespace");
                    }
                }
            }
            return createUnaryOperation(parseFactor(), operation);
        } else if (isOperator(peek())) {
            throw new RuntimeException("Unexpected operator: " + peek() + " on position " + index);
        } else if (peek() != '\0') {
            throw new RuntimeException("Unexpected character: " + peek() + " on position " + index);
        } else {
            throw new RuntimeException("Unexpected end of expression");
        }
    }

    private String parseUnaryOperator() {
        StringBuilder operation = new StringBuilder();
        while (isUnaryOperationPart(peek()) && operation.length() != 4) {
            operation.append(peek());
            movePointer();
        }
        return operation.toString();
    }

    private int parseNumber(boolean isNegative) {
        StringBuilder number = new StringBuilder();
        if (isNegative) {
            number.append("-");
        }
        while (Character.isDigit(peekWithoutSkipWhitespace())) {
            number.append(expression.charAt(index++));
        }
        try {
            return Integer.parseInt(number.toString());
        } catch (NumberFormatException e) {
            throw new RuntimeException("Invalid number format: " + number, e);
        }
    }

    private String parseVariable() {
        StringBuilder variable = new StringBuilder();
        while (index < expression.length() && isVariable(peek())) {
            variable.append(expression.charAt(index++));
        }
        String var = variable.toString();
        if (!var.equals("x") && !var.equals("y") && !var.equals("z")) {
            throw new RuntimeException("Invalid variable: " + var + " on position " + index);
        }
        return var;
    }

    private TripleExpression createBinaryOperation(TripleExpression leftOp, TripleExpression rightOp, char operation) {
        return switch (operation) {
            case '+' -> new CheckedAdd((BasicExpressionInterface) leftOp, (BasicExpressionInterface) rightOp);
            case '-' -> new CheckedSubtract((BasicExpressionInterface) leftOp, (BasicExpressionInterface) rightOp);
            case '*' -> new CheckedMultiply((BasicExpressionInterface) leftOp, (BasicExpressionInterface) rightOp);
            case '/' -> new CheckedDivide((BasicExpressionInterface) leftOp, (BasicExpressionInterface) rightOp);
            case '^' -> new Xor((BasicExpressionInterface) leftOp, (BasicExpressionInterface) rightOp);
            case '|' -> new Or((BasicExpressionInterface) leftOp, (BasicExpressionInterface) rightOp);
            case '&' -> new And((BasicExpressionInterface) leftOp, (BasicExpressionInterface) rightOp);
            default -> throw new RuntimeException("Unknown binary operator: " + operation);
        };
    }

    private TripleExpression createUnaryOperation(TripleExpression operand, String operation) {
        return switch (operation) {
            case "-" -> new CheckedNegate((BasicExpressionInterface) operand);
            case "~" -> new Not(operand);
            case "log2" -> new CheckedLog2(operand);
            case "pow2" -> new CheckedPow2(operand);
            default -> throw new RuntimeException("Unknown unary operator: " + operation);
        };
    }

    private static boolean isLineSeparator(char c) {
        return c == '\n' || c == '\t' || c == '\r' || c == '\f';
    }

    private boolean isUnaryOperationPart(char c) {
        return c == 'p' || c == 'o' || c == 'w' || c == 'l' || c == 'g' || c == '2';
    }

    private boolean isVariable(char c) {
        return c == 'x' || c == 'y' || c == 'z';
    }

    private boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' ||
                c == '/' || c == '^' || c == '|' || c == '&';
    }

    private boolean isBrackets(char c) {
        return c == '(' || c == ')';
    }

    private boolean isLegalCharacter(char c) {
        return Character.isDigit(c) || isVariable(c) || isOperator(c) || isBrackets(c) ||
                Character.isWhitespace(c) || c == 'p' || c == 'o' || c == 'w' || c == 'g' || c == 'l' || c == '2';
    }

    private boolean isWhitespaceOrInvisible(char c) {
        return Character.isWhitespace(c) || isLineSeparator(c);
    }

    private void validateExpression() {
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (!isLegalCharacter(c)) {
                throw new RuntimeException("Invalid character: " + c + " on position " + index);
            }
        }
    }

    private void isCorrectBracketSequence() {
        int balance = 0;
        for (char c : expression.toCharArray()) {
            if (c == '(') {
                balance++;
            } else if (c == ')') {
                balance--;
            }
            if (balance < 0) {
                throw new RuntimeException("Unmatched closing parenthesis found");
            }
        }
        if (balance != 0) {
            throw new RuntimeException("Unmatched opening parenthesis found");
        }
    }

    private void checkNextSymbolAfterVarOrNumber() {
        skipWhitespace();
        if (index < expression.length()) {
            char nextChar = expression.charAt(index);
            if (!isOperator(nextChar) && nextChar != ')') {
                throw new RuntimeException("Error while parsing: unexpected character '" + nextChar + "' after number or variable");
            }
        }
    }

    private boolean match(char expected) {
        skipWhitespace();
        if (index < expression.length() && expression.charAt(index) == expected) {
            index++;
            return true;
        }
        return false;
    }

    private void movePointer() {
        if (index < expression.length()) {
            index++;
        }
    }

    private void expect(char expected) {
        if (peek() != expected) {
            throw new RuntimeException("Expected '" + expected + "', actual: '" + peek() + "'");
        }
        movePointer();
    }

    private char peek() {
        skipWhitespace();
        if (index >= expression.length()) return '\0';
        return expression.charAt(index);
    }

    private char peekWithoutSkipWhitespace() {
        if (index >= expression.length()) return '\0';
        return expression.charAt(index);
    }

    private void skipWhitespace() {
        while (index < expression.length() && isWhitespaceOrInvisible(expression.charAt(index))) {
            index++;
        }
    }
}
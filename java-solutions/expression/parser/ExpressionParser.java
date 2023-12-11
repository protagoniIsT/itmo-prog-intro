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

    private static boolean isLineSeparator(char c) {
        return (c == '\n') || (c == '\u2028') || (c == '\u2029') ||
                (c == '\u0085') || (c == '\r');
    }

    private TripleExpression parseExpression() {
        TripleExpression result = parseBitXor();
        while (index < expression.length()) {
            char operation = peek();
            if (operation == '|') {
                consume();
                result = new Or((BasicExpressionInterface) result, (BasicExpressionInterface) parseBitXor());
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
                consume();
                result = new Xor((BasicExpressionInterface) result, (BasicExpressionInterface) parseBitAnd());
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
                consume();
                result = new And((BasicExpressionInterface) result, (BasicExpressionInterface) parseAdditive());
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
            if (operation == '+') {
                consume();
                result = new Add((BasicExpressionInterface) result, (BasicExpressionInterface) parseTerm());
            } else if (operation == '-') {
                consume();
                result = new Subtract((BasicExpressionInterface) result, (BasicExpressionInterface) parseTerm());
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
            if (operation == '*') {
                consume();
                result = new Multiply((BasicExpressionInterface) result, (BasicExpressionInterface) parseFactor());
            } else if (operation == '/') {
                consume();
                result = new Divide((BasicExpressionInterface) result, (BasicExpressionInterface) parseFactor());
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
            consume();
            TripleExpression result = parseExpression();
            expect();
            return result;
        } else if (currentChar == '-') {
            consume();
            return parseNegativeExpression();
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
            consume();
            skipWhitespace();
        }
        TripleExpression result;
        boolean isNegative = minusCount % 2 != 0;
        if (peek() == '(') {
            consume();
            result = parseExpression();
            expect();
            if (isNegative) {
                result = new Negate(result);
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
                result = new Negate(result);
            }
        }
        return result;
    }

    private char peekNext() {
        if (index + 1 >= expression.length()) {
            return '\0';
        }
        return expression.charAt(index + 1);
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

    private void consume() {
        if (index < expression.length()) {
            index++;
        }
    }

    private void expect() {
        if (peek() != ')') {
            throw new RuntimeException("Expected '" + ')' + "' but found '" + peek() + "'");
        }
        consume();
    }

    private char peek() {
        skipWhitespace();
        if (index >= expression.length()) return '\0';
        return expression.charAt(index);
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

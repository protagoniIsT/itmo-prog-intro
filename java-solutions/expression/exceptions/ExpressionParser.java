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

    private static boolean isLineSeparator(char c) {
        return (c == '\n') || (c == '\u2028') || (c == '\u2029') ||
                (c == '\u0085') || (c == '\r');
    }

    private TripleExpression parseExpression() {
        isCorrectBracketSequence();
        TripleExpression result = parseTerm();
        while (true) {
            if (match('+')) {
                result = new CheckedAdd((BasicExpressionInterface) result, (BasicExpressionInterface) parseTerm());
            } else if (match('-')) {
                result = new CheckedSubtract((BasicExpressionInterface) result, (BasicExpressionInterface) parseTerm());
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
                result = new CheckedMultiply((BasicExpressionInterface) result, (BasicExpressionInterface) parseFactor());
            } else if (match('/')) {
                result = new CheckedDivide((BasicExpressionInterface) result, (BasicExpressionInterface) parseFactor());
            } else {
                break;
            }
        }
        return result;
    }

    private void validateExpression() {
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (!isValidCharacterForValidation(c)) {
                throw new RuntimeException("Invalid character: " + c);
            }
        }
    }

    private boolean isValidCharacterForValidation(char c) {
        return Character.isDigit(c) || isVariable(c) || isOperator(c) || isBrackets(c) || Character.isWhitespace(c);
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

    private TripleExpression parseFactor() {
        skipWhitespace();
        if (match('(')) {
            TripleExpression result = parseExpression();
            expect();
            return result;
        } else if (match('-')) {
            if (Character.isDigit(peek())) {
                return new Const(parseNumber(true));
            }
            return new CheckedNegate((BasicExpressionInterface) parseFactor());
        } else if (Character.isDigit(peek())) {
            TripleExpression result = new Const(parseNumber(false));
            checkNextSymbolAfterVarOrNumberWithSkipWhitespaces();
            return result;
        } else if (isVariable(peek())) {
            TripleExpression result = new Variable(parseVariable());
            checkNextSymbolAfterVarOrNumberWithSkipWhitespaces();
            return result;
        } else if (isOperator(peek())) {
            throw new RuntimeException("Unexpected operator: " + peek());
        } else if (peek() != '\0') {
            throw new RuntimeException("Unexpected character: " + peek());
        } else {
            throw new RuntimeException("Unexpected end of expression");
        }
    }

    private void checkNextSymbolAfterVarOrNumberWithSkipWhitespaces() {
        skipWhitespace();
        if (index < expression.length()) {
            char nextChar = expression.charAt(index);
            if (!isOperator(nextChar) && nextChar != ')') {
                throw new RuntimeException("Syntax error: unexpected character '" + nextChar + "' after number or variable");
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

    private boolean isVariable(char c) {
        return c == 'x' || c == 'y' || c == 'z';
    }

    private boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    private boolean isBrackets(char c) {
        return c == ')' || c == '(';
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
            throw new RuntimeException("Invalid variable: " + var);
        }
        return var;
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

    private char peekWithoutSkipWhitespace() {
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
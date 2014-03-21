package md.zoidberg.java.evaluator;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by gfv on 3/21/14.
 */
public class Evaluator {


    List<Token> tokens = new ArrayList<>();
    Queue<Token> rpnExpression = new ArrayDeque<>();

    String expression;
    int tokenizePosition = 0;

    public String evaluate(String exp) throws EvaluationException, ArithmeticException {
        this.expression = exp;

        // account for unary plus and minus
        expression = expression.replace(" ", "").replace("(-", "(0-").replace("(+", "(0+")
                               .replace("/-", "*(0-1.0)/").replace("/+", "/").replace("*-", "*(-1.0)*")
                               .replace("*+", "*");
        if (expression.startsWith("-")) expression = "0" + expression;
        else if (expression.startsWith("+")) expression = "0" + expression;

        tokenize();

        Double result = 0.0;
        try {
            shuntingYard();
            result = eval();
        } catch (EmptyStackException ex) {
            throw new EvaluationException("Unbalanced operators");
        }

        if (result.isInfinite()) {
            throw new ArithmeticException("Division by zero");
        }
        return result.toString();
    }

    private Double eval() throws EvaluationException {
        Stack<Double> evalStack = new Stack<>();
        for (Token token: rpnExpression) {
            if (token.type == Token.Type.NUMBER) {
                evalStack.push(token.getValue());
            } else if (token.isOperator()) {
                double secondOperand = evalStack.pop();
                double firstOperand = evalStack.pop();

                double result;
                switch (token.type) {
                    case PLUS: result = firstOperand + secondOperand; break;
                    case MINUS:result = firstOperand - secondOperand; break;
                    case MULTIPLY:result = firstOperand * secondOperand; break;
                    case DIVIDE: result = firstOperand / secondOperand; break;
                    default:throw new IllegalStateException("Unknown token in RPN queue: " + token.type.toString());
                }

                evalStack.push(result);
            }
        }

        if (evalStack.size() != 1) {
            throw new EvaluationException("Too many numbers at the end of evaluation");
        }

        return evalStack.pop();
    }

    private void shuntingYard() throws EvaluationException {
        Queue<Token> rpn = new ArrayDeque<>();
        Stack<Token> operatorStack = new Stack<>();
        for (Token token: tokens) {
            if (token.type == Token.Type.NUMBER) {
                rpn.add(token);
            }  else if (token.type == Token.Type.LBRACK) {
                operatorStack.push(token);
            } else if (token.type == Token.Type.RBRACK) {
                while (!operatorStack.empty() && !(operatorStack.lastElement().type == Token.Type.LBRACK)) {
                    rpn.add(operatorStack.pop());
                }

                operatorStack.pop();
            } else {
                while (!operatorStack.empty()
                        && operatorStack.lastElement().isOperator()
                        && token.type.getPrecedence() <= operatorStack.lastElement().type.getPrecedence()) {
                    rpn.add(operatorStack.pop());
                }
                operatorStack.push(token);
            }
        }

        while (!operatorStack.empty()) {
            rpn.add(operatorStack.pop());
        }

        rpnExpression = rpn;
    }

    private void tokenize() throws EvaluationException {
        int brackets = 0;
        while (tokenizePosition < expression.length()) {
            switch (expression.charAt(tokenizePosition)) {
                case '+':
                    tokens.add(new Token(Token.Type.PLUS));
                    tokenizePosition++;
                    break;
                case '-':
                    tokens.add(new Token(Token.Type.MINUS));
                    tokenizePosition++;
                    break;
                case '*':
                    tokens.add(new Token(Token.Type.MULTIPLY));
                    tokenizePosition++;
                    break;
                case '/':
                    tokens.add(new Token(Token.Type.DIVIDE));
                    tokenizePosition++;
                    break;
                case '(':
                    tokens.add(new Token(Token.Type.LBRACK));
                    brackets++;
                    tokenizePosition++;
                    break;
                case ')':
                    tokens.add(new Token(Token.Type.RBRACK));
                    brackets--;
                    if (brackets < 0) throw new EvaluationException("Bracket imbalance");
                    tokenizePosition++;
                    break;
                default:
                    parseNumber();
            }
        }

        if (brackets != 0) throw new EvaluationException("Bracket imbalance");
    }

    private void parseNumber() throws EvaluationException {
        Pattern pattern = Pattern.compile("^(((\\d+\\.\\d*)|(\\d+)|(\\.\\d+))(E(-|\\+|)\\d+)?)");
        Matcher matcher = pattern.matcher(expression.substring(tokenizePosition));
        if (!matcher.find()) {
            throw new EvaluationException("Number expected");
        }

        String number = matcher.group(0);

        tokens.add(new Token(Token.Type.NUMBER, Double.parseDouble(number)));
        tokenizePosition += matcher.group(0).length();
    }
}

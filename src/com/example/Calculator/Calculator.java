package com.example.Calculator;
import java.util.LinkedList;

public class Calculator {
    String expression;
    LinkedList<Double> digits;
    LinkedList<Character> operators;
    public Calculator(String s) {
        this.expression = s;
        this.digits = new LinkedList<Double>();
        this.operators = new LinkedList<Character>();
    }

    public boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    public int prior(char c) {
        switch (c) {
            case('+'):
            case('-'): return 1;
            case('*'):
            case('/'):return 2;
            default: return -1;
        }
    }

    public void calculate(char oper) {
        if (oper == 'p') {
            digits.add(digits.removeLast());
            return;
        }
        if (oper == 'm') {
            digits.add((-1)*digits.removeLast());
            return;
        }
        if (digits.size() < 2) return;
        double right = digits.removeLast();
        double left = digits.removeLast();
        switch(oper){
            case('+'): {
                digits.add(right + left);
                break;
            }
            case('-'): {
                digits.add(right - left);
                break;
            }
            case('*'): {
                digits.add(right * left);
                break;
            }
            case('/'): {
                digits.add(left / right);
                break;
            }
        }
    }

    public double evaluate() {
        char c;
        int i = 0;
        while (i < expression.length()) {
            c = expression.charAt(i);
            if (c == '(') {
                operators.add(c);
                i++;
                continue;
            }
            if (c == ')') {
                while(operators.getLast() != '(') calculate(operators.removeLast());
                operators.removeLast();
                i++;
                continue;
            }
            if (isOperator(c)) {
                while (!operators.isEmpty() && prior(operators.getLast()) >= prior(c))
                    calculate(operators.removeLast());
                if (expression.charAt(i-1) != '(') operators.add(c);
                else operators.add((c == '+') ? 'p' : 'm');
                i++;
                continue;
            }
            String digit = "";
            while (i < expression.length() && (Character.isDigit(expression.charAt(i)) || expression.charAt(i) == '.')) {
                digit += expression.charAt(i);
                i++;
            }
            digits.add(Double.parseDouble(digit));
        }
        while (!operators.isEmpty())
            calculate(operators.removeLast());
        if (digits.size() == 0) return 0;
        return digits.get(0);  // возврат результата
    }
}

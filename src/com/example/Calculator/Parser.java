package com.example.Calculator;

public class Parser {
    public Double operationParser(String str) throws CalculationException{
        return orderPlus(str);
    }

    public Double orderPlus(String str) throws CalculationException {
        char[] action = {'+', '-'};
        int pointer = findOperation(str, action);
        if (pointer == -1) {
            return orderTimes(str);
        }else {
            if (pointer == 0 && str.charAt(pointer) == '+') {
                return orderPlus(str.substring(1, str.length()));
            }else {
                if (str.charAt(pointer) == '+') {
                    return orderPlus(str.substring(0, pointer)) + orderPlus(str.substring(pointer + 1));
                }else {
                    if (pointer == 0) {
                        return 0 - orderPlus(str.substring(pointer + 1));
                    }
                    return orderPlus(str.substring(0,pointer)) - orderPlus(str.substring(pointer + 1));
                }
            }
        }
    }

    public Double orderTimes(String str) throws CalculationException {
        char[] action = {'*', '/'};
        int pointer = findOperation(str, action);
        if (pointer == -1) {
            return orderBrackets(str);
        }else {
            if (str.charAt(pointer) == '*') {
                return orderPlus(str.substring(0, pointer)) * orderPlus(str.substring(pointer + 1));
            }else {
                Double right = orderPlus(str.substring(pointer + 1));
                if (right == 0) {
                    throw new CalculationException("Деление на ноль");
                }else {
                    return orderPlus(str.substring(0,pointer)) / right;
                }
            }
        }
    }

    public Double orderBrackets(String str) throws CalculationException {
        if (str.charAt(0) == '('){
            return orderPlus(str.substring(1, str.length() - 1));
        }else{
            return orderConst(str);
        }
    }

    public Double orderConst(String str) {
        if (str.charAt(0) == '+') {
            str = str.substring(1, str.length());
        }
        return Double.parseDouble(str);
    }

    public int findOperation(String str, char[] actions) {
        int pointer = str.length() - 1;
        boolean flag = true;
        for (int i = 0; i < actions.length - 1; i++) {
            flag = (flag && str.charAt(pointer) != actions[i]);
        }
        while (flag) {
            if (str.charAt(pointer) == ')') {
                int brackets = 1;
                while (brackets != 0) {
                    pointer--;
                    if (str.charAt(pointer) == ')') {
                        brackets++;
                    }
                    if (str.charAt(pointer) == '(') {
                        brackets--;
                    }
                }
            }
            if (pointer == 0) {
                return -1;
            }
            pointer--;
            for (int i = 0; i < actions.length; i++) {
                flag = (flag && str.charAt(pointer) != actions[i]);
            }
        }
        return pointer;
    }
}

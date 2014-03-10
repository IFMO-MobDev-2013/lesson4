package com.alimantu.SC;

/**
 * Created with IntelliJ IDEA.
 * User: PWR
 * Date: 12.01.14
 * Time: 2:57
 * To change this template use File | Settings | File Templates.
 */

public class Parser {

    final int NONE = 0;
    final int DELIMITER = 1;
    final int VARIABLE = 2;
    final int NUMBER = 3;

    final int SYNTAXERROR = 0;
    final int UNBALPARENS = 1;
    final int NOEXP = 2;
    final int DIVBYZERO = 3;

    final String EOF = "\0";

    private String exp;
    private int explds;
    private String token;
    private int tokType;


    public String toString(){
        return String.format("Exp = {0}\nexplds = {1}\nToken = {2}\nTokType = {3}", exp.toString(), explds,
                token.toString(), tokType);
    }

    private void getToken(){
        tokType = NONE;
        token = "";

        if(explds == exp.length()){
            token = EOF;
            return;
        }

        while(explds < exp.length() && Character.isWhitespace(exp.charAt(explds)))
            ++ explds;

        if(explds == exp.length()){
            token = EOF;
            return;
        }
        if(isDelim(exp.charAt(explds))){
            token += exp.charAt(explds);
            explds++;
            tokType = DELIMITER;
        }
        else if(Character.isLetter(exp.charAt(explds))){
            while(!isDelim(exp.charAt(explds))){
                token += exp.charAt(explds);
                explds++;
                if(explds >= exp.length())
                    break;
            }
            tokType = VARIABLE;
        }
        else if (Character.isDigit(exp.charAt(explds))){
            while(!isDelim(exp.charAt(explds))){
                token += exp.charAt(explds);
                explds++;
                if(explds >= exp.length())
                    break;
            }
            tokType = NUMBER;
        }
        else {
            token = EOF;
            return;
        }
    }

    private boolean isDelim(char charAt) {
        if((" +-/*%^=()".indexOf(charAt)) != -1)
            return true;
        return false;
    }

    public double evaluate(String expstr) throws ParserException{

        double result;

        exp = expstr;
        explds = 0;
        getToken();

        if(token.equals(EOF))
            handleErr(NOEXP);

        result = evalExp2();

        if(!token.equals(EOF))
            handleErr(SYNTAXERROR);

        return result;
    }

    private double evalExp2() throws ParserException{

        char op;
        double result;
        double partialResult;
        result = evalExp3();
        while((op = token.charAt(0)) == '+' ||
                op == '-'){
            getToken();
            partialResult = evalExp3();
            switch(op){
                case '-':
                    result -= partialResult;
                    break;
                case '+':
                    result += partialResult;
                    break;
            }
        }
        return result;
    }

    private double evalExp3() throws ParserException{

        char op;
        double result;
        double partialResult;

        result = evalExp4();
        while((op = token.charAt(0)) == '*' ||
                op == '/' | op == '%'){
            getToken();
            partialResult = evalExp4();
            switch(op){
                case '*':
                    result *= partialResult;
                    break;
                case '/':
                    if(partialResult == 0.0)
                        handleErr(DIVBYZERO);
                    result /= partialResult;
                    break;
                case '%':
                    if(partialResult == 0.0)
                        handleErr(DIVBYZERO);
                    result %= partialResult;
                    break;
            }
        }
        return result;
    }

    private double evalExp4() throws ParserException{

        double result;
        double partialResult;
        double ex;
        int t;
        result = evalExp5();
        if(token.equals("^")){
            getToken();
            partialResult = evalExp4();
            ex = result;
            if(partialResult == 0.0){
                result = 1.0;
            }else
                for(t = (int)partialResult - 1; t >  0; t--)
                    result *= ex;
        }
        return result;
    }

    private double evalExp5() throws ParserException{
        double result;

        String op;
        op = " ";

        if((tokType == DELIMITER) && token.equals("+") ||
                token.equals("-")){
            op = token;
            getToken();
        }
        result = evalExp6();
        if(op.equals("-"))
            result =  -result;
        return result;
    }

    private double evalExp6() throws ParserException{
        double result;

        if(token.equals("(")){
            getToken();
            result = evalExp2();
            if(!token.equals(")"))
                handleErr(UNBALPARENS);
            getToken();
        }
        else
            result = atom();
        return result;
    }

    private double atom()   throws ParserException{

        double result = 0.0;
        switch(tokType){
            case NUMBER:
                try{
                    result = Double.parseDouble(token);
                }
                catch(NumberFormatException exc){
                    handleErr(SYNTAXERROR);
                }
                getToken();

                break;
            default:
                handleErr(SYNTAXERROR);
                break;
        }
        return result;
    }

    private void handleErr(int nOEXP2) throws ParserException{

        String[] err  = {
                "Syntax error",
                "Unbalanced Parentheses",
                "No Expression Present",
                "Division by zero"
        };
        throw new ParserException(err[nOEXP2]);
    }

}

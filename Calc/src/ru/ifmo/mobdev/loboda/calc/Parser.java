package ru.ifmo.mobdev.loboda.calc;

public class Parser {
    String s;
    int pos;
    Number number;
    Token curToken;

    public static Number parse(String s){
        if(s.equals("")){
            return new Number("0");
        }
        Parser parser = new Parser(s);
        return parser.expr();
    }

    private Parser(String s){
        this.s = s;
        pos = 0;
    }

    private Token getToken(){
        if(pos == s.length()){
            return Token.END;
        }
        char ch = s.charAt(pos);
        ++pos;
        Token token;
        switch (ch){
            case '+':
                token = Token.PLUS;
                break;
            case '-':
                token = Token.MINUS;
                break;
            case '*':
                token = Token.MUL;
                break;
            case '/':
                token = Token.DIV;
                break;
            case '(':
                token = Token.LP;
                break;
            case ')':
                token = Token.RP;
                break;
            default:
                --pos;
                String num = "";
                while((ch >= '0' && ch <= '9') || ch == '.'){
                    num = num.concat(new Character(ch).toString());
                    ++pos;
                    if(pos != s.length()){
                        ch = s.charAt(pos);
                    } else {
                        break;
                    }
                }
                number = new Number(num);
                token = Token.NUMBER;
        }
        curToken = token;
        return token;
    }

    private Number prim(){
        curToken = getToken();
        if(curToken == Token.NUMBER){
            Number value = number;
            getToken();
            return value;
        }
        if(curToken == Token.LP){
            Number value = expr();
            getToken();
            return value;
        }
        if(curToken == Token.MINUS){
            return prim().negate();
        }
        return prim();
    }

    private Number term(){
        Number left = prim();
        for(;;){
            if(curToken == Token.MUL){
                left = left.mul(prim());
                continue;
            }
            if(curToken == Token.DIV){
                left = left.div(prim());
                continue;
            }
            return left;
        }
    }

    private Number expr(){
        Number left = term();
        for(;;){
            if(curToken == Token.PLUS){
                left = left.plus(term());
                continue;
            }
            if(curToken == Token.MINUS){
                left = left.minus(term());
                continue;
            }
            return left;
        }
    }
}

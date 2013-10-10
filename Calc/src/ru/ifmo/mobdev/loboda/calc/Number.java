package ru.ifmo.mobdev.loboda.calc;

import java.math.BigInteger;

public class Number {
    BigInteger intRep;
    double doubleRep;
    boolean isInt;
    public Number(String s){
        try{
            intRep = new BigInteger(s);
            isInt = true;
        } catch (NumberFormatException e){
            doubleRep = Double.parseDouble(s);
            isInt = false;
        }
    }
    public Number(double val){
        doubleRep = val;
        isInt = false;
    }
    public Number(BigInteger val){
        intRep = val;
        isInt = true;
    }
    public String toString(){
        if(isInt){
            return intRep.toString();
        } else {
            return (new Double(doubleRep)).toString();
        }
    }
    public Number mul(Number b){
        if(isInt && b.isInt){
            return new Number(intRep.multiply(b.intRep));
        } else {
            return new Number(Double.parseDouble(toString()) * Double.parseDouble(b.toString()));
        }
    }
    public Number div(Number b){
        if(isInt && b.isInt && !b.toString().equals("0")){
             BigInteger bigInteger = intRep.divide(b.intRep);
             if(intRep.equals(bigInteger.multiply(b.intRep))){
                return new Number(bigInteger);
             } else {
                 return new Number(Double.parseDouble(toString()) / Double.parseDouble(b.toString()));
             }
        } else {
            return new Number(Double.parseDouble(toString()) / Double.parseDouble(b.toString()));
        }
    }
    public Number plus(Number b){
        if(isInt && b.isInt){
            return new Number(intRep.add(b.intRep));
        } else {
            return new Number(Double.parseDouble(toString()) + Double.parseDouble(b.toString()));
        }
    }
    public Number minus(Number b){
        if(isInt && b.isInt){
            return new Number(intRep.subtract(b.intRep));
        } else {
            return new Number(Double.parseDouble(toString()) - Double.parseDouble(b.toString()));
        }
    }
    public Number negate(){
        return mul(new Number("-1"));
    }

    public boolean equals(Object object){
        if(object instanceof Number){
            if(((Number) object).isInt && isInt){
                return (((Number) object).intRep.equals(intRep));
            }
            if(!((Number) object).isInt && !isInt){
                return ((Number) object).doubleRep == doubleRep;
            }
            return false;
        } else {
            return false;
        }
    }
}

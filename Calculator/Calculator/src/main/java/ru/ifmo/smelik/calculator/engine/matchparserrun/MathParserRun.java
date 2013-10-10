package ru.ifmo.smelik.calculator.engine.matchparserrun;

import ru.ifmo.smelik.calculator.engine.matchParser.MathParser;


public class MathParserRun {

    public static String evaluate(String arg) {
        MathParser p = new MathParser();
        try{
            Double result = p.Parse( arg );
            if ((result % 1) != 0.0) {
                return Double.toString(p.Parse( arg ));
            } else {
                return Long.toString(result.longValue());
            }

        }catch(Exception e){
            return e.getMessage();
        }
    }
}

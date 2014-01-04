package ru.ifmo.mobdev.calculator.engine.matchparserrun;

import ru.ifmo.mobdev.calculator.engine.matchParser.MathParser;


public class MathParserRun {

    public static Double evaluate(String arg) {
        MathParser p = new MathParser();
        try{
            Double result = p.Parse( arg );
                return result;
        }catch(Exception e){
            return null;
        }
    }

}

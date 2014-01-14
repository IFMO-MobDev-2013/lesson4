package com.mobdev.calculator;

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

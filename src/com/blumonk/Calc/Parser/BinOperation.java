package com.blumonk.Calc.Parser;

public class BinOperation implements Evaluable {
	private BinaryOperation functional;
	private Evaluable first;
	private Evaluable second;
	
	public BinOperation(Evaluable first, Evaluable second, BinaryOperation functional) {
		this.first = first;
		this.second = second;
		this.functional = functional;
	}
	
	@Override
	public double evaluate() throws CalcException {
 		return functional.apply(first.evaluate(), second.evaluate());
	}
	
}

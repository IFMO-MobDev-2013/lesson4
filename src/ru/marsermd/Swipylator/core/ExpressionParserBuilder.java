package ru.marsermd.Swipylator.core;

import java.math.BigDecimal;
import java.math.BigInteger;

public class ExpressionParserBuilder {

	public static ExpressionParser<Integer> buildForInteger(){
		return new ExpressionParser<Integer>(IntegerBinaryOperator.PLUS, 
				IntegerBinaryOperator.MINUS, IntegerBinaryOperator.TIMES, 
				IntegerBinaryOperator.DIVISION, IntegerBinaryOperator.MODULE, 
				IntegerUnaryOperator.MINUS, IntegerUnaryOperator.PLUS, 
				new ConstFinder<Integer>() {

					@Override
					public Integer find(String token) {
						return Integer.parseInt(token);
					}
				});
	}
	
	public static ExpressionParser<Double> buildForDouble(){
		return new ExpressionParser<Double>(DoubleBinaryOperator.PLUS, 
				DoubleBinaryOperator.MINUS, DoubleBinaryOperator.TIMES, 
				DoubleBinaryOperator.DIVISION, DoubleBinaryOperator.MODULE, 
				DoubleUnaryOperator.MINUS, DoubleUnaryOperator.PLUS, 
				new ConstFinder<Double>() {

					@Override
					public Double find(String token) {
						return Double.parseDouble(token);
					}
				});
	}
	
	public static ExpressionParser<BigInteger> buildForBigInteger(){
		return new ExpressionParser<BigInteger>(BigIntegerBinaryOperator.PLUS, 
				BigIntegerBinaryOperator.MINUS, BigIntegerBinaryOperator.TIMES, 
				BigIntegerBinaryOperator.DIVISION, BigIntegerBinaryOperator.MODULE, 
				BigIntegerUnaryOperator.MINUS, BigIntegerUnaryOperator.PLUS, 
				new ConstFinder<BigInteger>() {
					@Override
					public BigInteger find(String token) {
						return (new BigDecimal(token)).toBigInteger();
					}
				});
	}

    public static ExpressionParser<BigDecimal> buildForBigDecimal(){
        return  new ExpressionParser<BigDecimal>(BigDecimalBinaryOperator.PLUS,
                BigDecimalBinaryOperator.MINUS, BigDecimalBinaryOperator.TIMES,
                BigDecimalBinaryOperator.DIVISION, BigDecimalBinaryOperator.MODULE,
                BigDecimalUnaryOperator.MINUS, BigDecimalUnaryOperator.PLUS,
                new ConstFinder<BigDecimal>() {
                    @Override
                    public BigDecimal find(String token) {
                        return new BigDecimal(token);
                    }
                });
    }

}

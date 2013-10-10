package com.ifmomd.CalqLater;

import java.math.BigInteger;

public class Operations {

	public static enum IntegerOperations implements Operator<Integer> {
		ADD {
			@Override
			public Integer apply(Integer operand1, Integer operand2) {
				return operand1 + operand2;
			}
		},
		SUBTRACT {
			@Override
			public Integer apply(Integer operand1, Integer operand2) {
				return operand1 - operand2;
			}
		},
		MULTIPLY {
			@Override
			public Integer apply(Integer operand1, Integer operand2) {
				return operand1 * operand2;
			}
		},
		DIVIDE {
			@Override
			public Integer apply(Integer operand1, Integer operand2) {
				return operand1 / operand2;
			}
		},
		MODULO {
			@Override
			public Integer apply(Integer operand1, Integer operand2) {
				return operand1 % operand2;
			}
		};

	}

	public static enum DoubleOperations implements Operator<Double> {
		ADD {
			@Override
			public Double apply(Double operand1, Double operand2) {
				return operand1 + operand2;
			}
		},
		SUBTRACT {
			@Override
			public Double apply(Double operand1, Double operand2) {
				return operand1 - operand2;
			}
		},
		MULTIPLY {
			@Override
			public Double apply(Double operand1, Double operand2) {
				return operand1 * operand2;
			}
		},
		DIVIDE {
			@Override
			public Double apply(Double operand1, Double operand2) {
				return operand1 / operand2;
			}
		},
		MODULO {
			@Override
			public Double apply(Double operand1, Double operand2) {
				return operand1 % operand2;
			}
		};

	}

	public static enum BigIntegerOperations implements Operator<BigInteger> {
		ADD {
			@Override
			public BigInteger apply(BigInteger operand1, BigInteger operand2) {
				return operand1.add(operand2);
			}
		},
		SUBTRACT {
			@Override
			public BigInteger apply(BigInteger operand1, BigInteger operand2) {
				return operand1.subtract(operand2);
			}
		},
		MULTIPLY {
			@Override
			public BigInteger apply(BigInteger operand1, BigInteger operand2) {
				return operand1.multiply(operand2);
			}
		},
		DIVIDE {
			@Override
			public BigInteger apply(BigInteger operand1, BigInteger operand2) {
				return operand1.divide(operand2);
			}
		},
		MODULO {
			@Override
			public BigInteger apply(BigInteger operand1, BigInteger operand2) {
				return operand1.mod(operand2);
			}
		};

	}

}

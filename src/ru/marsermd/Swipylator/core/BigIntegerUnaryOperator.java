package ru.marsermd.Swipylator.core;

import java.math.BigInteger;

public enum BigIntegerUnaryOperator implements UnaryOperator<BigInteger>{
	PLUS {
		@Override
		public BigInteger calculate(BigInteger body) {
			return body;
		}
	},
	MINUS {
		@Override
		public BigInteger calculate(BigInteger body) {
			return body.negate();
		}
	}
}

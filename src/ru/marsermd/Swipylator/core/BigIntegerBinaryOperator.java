package ru.marsermd.Swipylator.core;

import java.math.BigInteger;

public enum BigIntegerBinaryOperator implements BinaryOperator<BigInteger> {
	PLUS {
		@Override
		public BigInteger calculate(BigInteger left, BigInteger right) {
			return left.add(right);
		}
	},
	MINUS {
		@Override
		public BigInteger calculate(BigInteger left, BigInteger right) {
			return left.subtract(right);
		}
	},
	TIMES {
		@Override
		public BigInteger calculate(BigInteger left, BigInteger right) {
			return left.multiply(right);
		}
	},
	DIVISION {
		@Override
		public BigInteger calculate(BigInteger left, BigInteger right) {
			return left.divide(right);
		}
	},
	MODULE {
		@Override
		public BigInteger calculate(BigInteger left, BigInteger right) {
			return left.mod(right);
		}
	}
}

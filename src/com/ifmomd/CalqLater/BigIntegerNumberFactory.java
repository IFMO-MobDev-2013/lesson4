package com.ifmomd.CalqLater;

import java.math.BigInteger;

public class BigIntegerNumberFactory extends NumberFactory<BigInteger> {

	@Override
	public BigInteger parseNumber(String s) {
		return new BigInteger(s);
	}

	@Override
	String getPattern() {
		return "^[-+]?[0-9]*$";
	}

	@Override
	BigInteger getNull() {
		return BigInteger.valueOf(0);
	}

}

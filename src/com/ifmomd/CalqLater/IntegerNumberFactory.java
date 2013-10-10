package com.ifmomd.CalqLater;

public class IntegerNumberFactory extends NumberFactory<Integer> {

	@Override
	public Integer parseNumber(String s) {
		return Integer.parseInt(s);
	}

	@Override
	String getPattern() {
		return "^[-+]?[0-9]{1,8}$";
	}

	@Override
	Integer getNull() {
		return 0;
	}

}

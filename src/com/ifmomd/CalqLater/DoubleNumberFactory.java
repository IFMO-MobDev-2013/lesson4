package com.ifmomd.CalqLater;

public class DoubleNumberFactory extends NumberFactory<Double> {

	@Override
	public Double parseNumber(String s) {
		return Double.parseDouble(s);
	}

	@Override
	String getPattern() {
		return "^[-+]?[0-9]*\\.?[0-9]+([eE][-+]?[0-9]+)?$";
	}

	@Override
	Double getNull() {
		return 0.;
	}

}

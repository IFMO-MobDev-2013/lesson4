package com.example.Les4;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Alex on 14.01.14.
 */
public class ParserExpressionTest extends TestCase {
	ParserExpression parserExpression;

	@Before
	public void SetUp() {
	parserExpression = new ParserExpression();
	}

	@Test
	public void test1() throws Exception {
		assertEquals(1.0,parserExpression.Parser("1").evaluate());
	}
	@Test
	public void test2() throws Exception {
		assertEquals(9.0,parserExpression.Parser("(-3)*(-3)").evaluate());
	}
	@Test
	public void test3() throws Exception {
		assertEquals(3.0,parserExpression.Parser("(-(-3))").evaluate());
	}
	@Test
	public void test4() throws Exception {
		assertEquals(-6.0,parserExpression.Parser("-3-3.0").evaluate());
	}
	@Test
	public void test5() throws Exception {
		assertEquals(14.0,parserExpression.Parser("2*7").evaluate());
	}
	@Test
	public void test6() throws Exception {
		assertEquals(0.25,parserExpression.Parser("2/56*7").evaluate());
	}
	@Test
	public void test7() throws Exception {
		assertEquals(1.1666666666666667,parserExpression.Parser("7/(2*3)").evaluate());
	}
	@Test(expected = ExceptionDivisionByZero.class)
	public void test8() throws Exception {
		assertEquals(0,parserExpression.Parser("7/0").evaluate());
	}

}

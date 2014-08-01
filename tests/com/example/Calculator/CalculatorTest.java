package com.example.Calculator;
import org.junit.*;
import static org.junit.Assert.*;
public class CalculatorTest {
    private final double DELTA = 0.0000000001;
    private void test(String str, double expected) {
        Calculator calculator = new Calculator(str);
        assertEquals(expected, calculator.evaluate(), DELTA);
    }
    @Test
    public void testPlus() {
        test("23.5+27.9", 23.5 + 27.9);
    }
    @Test
    public void testMinus() {
        test("672.1-873.9", 672.1-873.9);
    }
    @Test
    public void testMul() {
        test("342*123", 342*123);
    }
    @Test
    public void testDivide() {
        test("81/9", 81/9);
    }
    @Test
    public void testBrackets() {
        test("(675+723)*34", (675+723)*34);
        test("(6234-1234)/10", (6234-1234)/10);
    }

    @Test
    public void testOthers() {
        test("(18*18)/9 + (12.5 + 12.5)/2 - ((-2)+65*28)", (18*18)/9 + (12.5 + 12.5)/2 - ((-2)+65*28));
        test("4+6*(45-34)*(56*(45*56))*45.0/5*(6)",4+6*(45-34)*(56*(45*56))*45.0/5*(6));
    }
}

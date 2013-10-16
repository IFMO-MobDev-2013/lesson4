package ru.marsermd.Swipylator.core;
import java.math.BigDecimal;

public enum BigDecimalUnaryOperator implements UnaryOperator<BigDecimal> {
    PLUS {
        @Override
        public BigDecimal calculate(BigDecimal body) {
            return body;
        }
    },
    MINUS {
        @Override
        public BigDecimal calculate(BigDecimal body) {
            return body.negate();
        }
    }
}
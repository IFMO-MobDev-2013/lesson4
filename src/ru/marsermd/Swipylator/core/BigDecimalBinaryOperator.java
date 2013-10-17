package ru.marsermd.Swipylator.core;
import android.util.Log;

import java.math.BigDecimal;

public enum BigDecimalBinaryOperator implements BinaryOperator<BigDecimal> {
    PLUS {
        @Override
        public BigDecimal calculate(BigDecimal left, BigDecimal right) {
            return left.add(right);
        }
    },
    MINUS {
        @Override
        public BigDecimal calculate(BigDecimal left, BigDecimal right) {
            return left.subtract(right);
        }
    },
    TIMES {
        @Override
        public BigDecimal calculate(BigDecimal left, BigDecimal right) {
            return left.multiply(right);
        }
    },
    DIVISION {
        @Override
        public BigDecimal calculate(BigDecimal left, BigDecimal right) {
            Log.e("divide", left.toString() + " " + right.toString());
            return left.divide(right);
        }
    },
    MODULE {
        @Override
        public BigDecimal calculate(BigDecimal left, BigDecimal right) {
            return left.remainder(right);
        }
    }
}

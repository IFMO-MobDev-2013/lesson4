package ru.zulyaev.ifmo.lesson4.parser.dbl;

import ru.zulyaev.ifmo.lesson4.parser.Function;

import java.util.List;

public enum DoublePositiveArgumentCountFunction implements Function<Double> {
    MIN {
        @Override
        public Double calc(List<? extends Double> arguments) {
            Double result = arguments.get(0);
            for (Double val : arguments) {
                result = Math.min(result, val);
            }
            return result;
        }
    },
    MAX {
        @Override
        public Double calc(List<? extends Double> arguments) {
            Double result = arguments.get(0);
            for (Double val : arguments) {
                result = Math.max(result, val);
            }
            return result;
        }
    };

    @Override
    public boolean accepts(int argumentCount) {
        return argumentCount > 0;
    }

    private String name = null;

    @Override
    public String toString() {
        if (name == null) {
            name = super.toString().toLowerCase();
        }

        return name;
    }
}

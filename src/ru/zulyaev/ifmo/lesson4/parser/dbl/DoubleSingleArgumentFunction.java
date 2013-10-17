package ru.zulyaev.ifmo.lesson4.parser.dbl;

import ru.zulyaev.ifmo.lesson4.parser.Function;

import java.util.List;

public enum DoubleSingleArgumentFunction implements Function<Double> {
    ABS {
        @Override
        public Double calc(Double value) {
            return Math.abs(value);
        }
    },
    COS {
        @Override
        public Double calc(Double value) {
            return Math.cos(value);
        }
    },
    LOG {
        @Override
        public Double calc(Double value) {
            return Math.log(value);
        }
    },
    SIN {
        @Override
        public Double calc(Double value) {
            return Math.sin(value);
        }
    },
    SQRT {
        @Override
        public Double calc(Double value) {
            return Math.sqrt(value);
        }
    },
    TAN {
        @Override
        public Double calc(Double value) {
            return Math.tan(value);
        }
    };

    public abstract Double calc(Double value);

    @Override
    public Double calc(List<? extends Double> arguments) {
        return calc(arguments.get(0));
    }

    @Override
    public boolean accepts(int argumentCount) {
        return argumentCount == 1;
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

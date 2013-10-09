package ru.georgeee.android.gcalc.calc.parser.token;

import ru.georgeee.android.gcalc.calc.expression.Expression;
import ru.georgeee.android.gcalc.calc.expression.basic.*;
import ru.georgeee.android.gcalc.calc.expression.integer.*;
import ru.georgeee.android.gcalc.calc.expression.real.*;
import ru.georgeee.android.gcalc.calc.expression.real.ahyp.ACosh;
import ru.georgeee.android.gcalc.calc.expression.real.ahyp.ACoth;
import ru.georgeee.android.gcalc.calc.expression.real.ahyp.ASinh;
import ru.georgeee.android.gcalc.calc.expression.real.ahyp.ATanh;
import ru.georgeee.android.gcalc.calc.expression.real.atrig.ACos;
import ru.georgeee.android.gcalc.calc.expression.real.atrig.ACot;
import ru.georgeee.android.gcalc.calc.expression.real.atrig.ASin;
import ru.georgeee.android.gcalc.calc.expression.real.atrig.ATan;
import ru.georgeee.android.gcalc.calc.expression.real.hyp.Cosh;
import ru.georgeee.android.gcalc.calc.expression.real.hyp.Coth;
import ru.georgeee.android.gcalc.calc.expression.real.hyp.Sinh;
import ru.georgeee.android.gcalc.calc.expression.real.hyp.Tanh;
import ru.georgeee.android.gcalc.calc.expression.real.trig.Cos;
import ru.georgeee.android.gcalc.calc.expression.real.trig.Cot;
import ru.georgeee.android.gcalc.calc.expression.real.trig.Sin;
import ru.georgeee.android.gcalc.calc.expression.real.trig.Tan;
import ru.georgeee.android.gcalc.calc.number.GNumber;

import java.lang.reflect.InvocationTargetException;

/**
 * Created with IntelliJ IDEA.
 * User: georgeee
 * Date: 05.10.13
 * Time: 6:34
 * To change this template use File | Settings | File Templates.
 */
public class TokenHolder {
    private static final int BRACKET_LEVEL = 0;
    private static final int CONST_LEVEL = BRACKET_LEVEL+1;
    private static final int FUNCTION_LEVEL = CONST_LEVEL+1;
    private static final int FACTORIAL_LEVEL = FUNCTION_LEVEL+1;
    private static final int POWER_LEVEL = FACTORIAL_LEVEL+1;
    private static final int MULTIPLY_LEVEL = POWER_LEVEL+1;
    private static final int NEGATE_LEVEL = MULTIPLY_LEVEL+1;
    private static final int ADD_LEVEL = NEGATE_LEVEL+1;
    private static final int SHIFT_LEVEL = ADD_LEVEL+1;
    private static final int AND_LEVEL = SHIFT_LEVEL+1;
    private static final int XOR_LEVEL = AND_LEVEL+1;
    private static final int OR_LEVEL = XOR_LEVEL+1;
    private static final int MAX_LEVEL = OR_LEVEL;
    private static final boolean IS_RIGHT_ASSOCIATIVE[] = new boolean[MAX_LEVEL + 1];

    static {
        IS_RIGHT_ASSOCIATIVE[FUNCTION_LEVEL] = true;
        IS_RIGHT_ASSOCIATIVE[NEGATE_LEVEL] = true;
    }

    public int getMaxLevel() {
        return MAX_LEVEL;
    }

    public int getMultiplyLevel(){
        return MULTIPLY_LEVEL;
    }

     public boolean isLevelRightAssociative(int level){
         if(level>MAX_LEVEL||level<0) return false;
         return IS_RIGHT_ASSOCIATIVE[level];
     }

    public final AhoTokenType multiplyTokenType = createAhoBothArgumentTokenType("*", Multiply.class, MULTIPLY_LEVEL);
    protected GNumber number;
    protected ManualTokenTypeFactory[] manualTokenTypeFactories = null;
    protected AhoTokenType[] ahoTokenTypes = null;

    public TokenHolder(GNumber number) {
        this.number = number;
    }

    protected static AhoTokenType createAhoBothArgumentTokenType(final String matchString, final Class<? extends Expression> exprClass, final int priority) {
        return new AhoTokenType() {
            @Override
            public String getMatchString() {
                return matchString;
            }

            @Override
            public Expression getExpression(Expression leftOperand, Expression rightOperand) {
                Expression expr = null;
                if (leftOperand != null && rightOperand != null)
                    try {
                        expr = exprClass.getConstructor(Expression.class, Expression.class).newInstance(leftOperand, rightOperand);
                    } catch (InstantiationException e) {
                        throw new RuntimeException(e);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    } catch (NoSuchMethodException e) {
                        throw new RuntimeException(e);
                    } catch (InvocationTargetException e) {
                        throw new RuntimeException(e);
                    }
                return expr;
            }

            @Override
            public int getArgumentType() {
                return BOTH_ARG_TYPE;
            }

            @Override
            public int getPriority() {
                return priority;
            }
        };
    }

    protected static AhoTokenType createFunctionTokenType(final String matchString, final Class<? extends Expression> exprClass) {
        return createAhoOneArgumentTokenType(matchString, exprClass, FUNCTION_LEVEL);
    }

    protected static AhoTokenType createAhoOneArgumentTokenType(final String matchString, final Class<? extends Expression> exprClass, final int priority) {
        return new AhoTokenType() {
            @Override
            public String getMatchString() {
                return matchString;
            }

            @Override
            public Expression getExpression(Expression leftOperand, Expression rightOperand) {
                Expression expr = null;
                Expression operand = IS_RIGHT_ASSOCIATIVE[getPriority()] ? rightOperand:leftOperand;
                if (operand != null)
                    try {
                        expr = exprClass.getConstructor(Expression.class).newInstance(operand);
                    } catch (InstantiationException e) {
                        throw new RuntimeException(e);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    } catch (NoSuchMethodException e) {
                        throw new RuntimeException(e);
                    } catch (InvocationTargetException e) {
                        throw new RuntimeException(e);
                    }
                return expr;
            }

            @Override
            public int getArgumentType() {
                return ONE_ARG_TYPE;
            }

            @Override
            public int getPriority() {
                return priority;
            }
        };
    }

    protected static AhoTokenType createAhoNoArgumentTokenType(final String matchString, final Class<? extends Expression> exprClass, final int priority) {
        return new AhoTokenType() {
            @Override
            public String getMatchString() {
                return matchString;
            }

            @Override
            public Expression getExpression(Expression leftOperand, Expression rightOperand) {
                Expression expr;
                try {
                    expr = exprClass.newInstance();
                } catch (InstantiationException e) {
                    throw new RuntimeException(e);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
                return expr;
            }

            @Override
            public int getArgumentType() {
                return NO_ARG_TYPE;
            }

            @Override
            public int getPriority() {
                return priority;
            }
        };
    }

    public ManualTokenTypeFactory[] getManualTokenTypeFabrics() {
        if (manualTokenTypeFactories == null) {
            manualTokenTypeFactories = getManualTokenTypeFabricsImpl();
        }
        return manualTokenTypeFactories;
    }

    protected ManualTokenTypeFactory[] getManualTokenTypeFabricsImpl() {
        return new ManualTokenTypeFactory[]{
                new ManualTokenTypeFactory() {
                    @Override
                    public TokenType getTokenType(final String part) {
                        try {
                            return new TokenType() {
                                GNumber value = number.parseFromString(part);

                                @Override
                                public Expression getExpression(Expression leftOperand, Expression rightOperand) {
                                    return value == null ? null : new Constant(value);
                                }

                                @Override
                                public int getArgumentType() {
                                    return TokenType.NO_ARG_TYPE;
                                }

                                @Override
                                public int getPriority() {
                                    return CONST_LEVEL;
                                }
                            };

                        } catch (NumberFormatException ex) {
                            return null;
                        }
                    }
                },
        };
    }

    public AhoTokenType[] getAhoTokenTypes() {
        if (ahoTokenTypes == null) {
            ahoTokenTypes = getAhoTokenTypesImpl();
        }
        return ahoTokenTypes;
    }

    protected AhoTokenType[] getAhoTokenTypesImpl() {
        return new AhoTokenType[]{
            /*Basic operators*/
                new PassUnchangedTokenType(),
                new NegateTokenType(),
                createAhoBothArgumentTokenType("+", Add.class, ADD_LEVEL),
                createAhoBothArgumentTokenType("-", Subtract.class, ADD_LEVEL),
                createAhoBothArgumentTokenType("/", Divide.class, MULTIPLY_LEVEL),
                multiplyTokenType,
                createAhoBothArgumentTokenType("^", Power.class, POWER_LEVEL),
            /*Integer operators*/
                createAhoBothArgumentTokenType("%", Mod.class, MULTIPLY_LEVEL),
                createAhoBothArgumentTokenType("&", And.class, AND_LEVEL),
                createAhoBothArgumentTokenType("<<", LShift.class, SHIFT_LEVEL),
                createAhoBothArgumentTokenType(">>", RShift.class, SHIFT_LEVEL),
                createAhoBothArgumentTokenType("XOR", Xor.class, XOR_LEVEL),
                createAhoBothArgumentTokenType("|", Or.class, OR_LEVEL),
                createAhoOneArgumentTokenType("!", Factorial.class, FACTORIAL_LEVEL),
                createAhoOneArgumentTokenType("~", Not.class, NEGATE_LEVEL),
            /*Functions*/
                createFunctionTokenType("exp", Exponent.class),
                createFunctionTokenType("√", Sqrt.class),
                createFunctionTokenType("lg", Lg.class),
                createFunctionTokenType("ln", Ln.class),
                createFunctionTokenType("log2", Log2.class),
                createFunctionTokenType("acos", ACos.class),
                createFunctionTokenType("asin", ASin.class),
                createFunctionTokenType("atan", ATan.class),
                createFunctionTokenType("acot", ACot.class),
                createFunctionTokenType("sin", Sin.class),
                createFunctionTokenType("cos", Cos.class),
                createFunctionTokenType("tan", Tan.class),
                createFunctionTokenType("cot", Cot.class),
                createFunctionTokenType("acosh", ACosh.class),
                createFunctionTokenType("asinh", ASinh.class),
                createFunctionTokenType("atanh", ATanh.class),
                createFunctionTokenType("acoth", ACoth.class),
                createFunctionTokenType("sinh", Sinh.class),
                createFunctionTokenType("cosh", Cosh.class),
                createFunctionTokenType("tanh", Tanh.class),
                createFunctionTokenType("coth", Coth.class),
            /*Brackets*/
                new OpenningBracketTokenType(),
                new ClosingBracketTokenType(),
            /*Consts*/
                createAhoNoArgumentTokenType("π", Pi.class, CONST_LEVEL),
                createAhoNoArgumentTokenType("pi", Pi.class, CONST_LEVEL),
                createAhoNoArgumentTokenType("e", ExponentConst.class, CONST_LEVEL),
        };
    }

    protected static class PassUnchangedTokenType extends NegateTokenType{
        @Override
        public String getMatchString() {
            return "+";
        }

        @Override
        protected Expression getExpressionImpl(Expression operand) {
            return new PassUnchanged(operand);
        }
    }

    protected static class NegateTokenType implements AhoTokenType{
            @Override
            public String getMatchString() {
                return "-";
            }

            @Override
            public Expression getExpression(Expression leftOperand, Expression rightOperand) {
                Expression expr = null;
                if (leftOperand == null //To set off possible conflicts with subtract
                        && rightOperand != null)
                    expr = getExpressionImpl(rightOperand);
                return expr;
            }

            protected Expression getExpressionImpl(Expression operand){
                return new Negate(operand);
            }

            @Override
            public int getArgumentType() {
                return ONE_ARG_TYPE;
            }

            @Override
            public int getPriority() {
                return NEGATE_LEVEL;
            }
    }

    public static class OpenningBracketTokenType extends BracketTokenType {

        @Override
        public boolean isOpenning() {
            return true;
        }

        @Override
        public String getMatchString() {
            return "(";
        }
    }

    public static class ClosingBracketTokenType extends BracketTokenType {

        @Override
        public boolean isOpenning() {
            return false;
        }

        @Override
        public String getMatchString() {
            return ")";
        }
    }

    protected abstract static class BracketTokenType implements AhoTokenType {
        @Override
        public int getArgumentType() {
            return NO_ARG_TYPE;
        }

        @Override
        public Expression getExpression(Expression leftOperand, Expression rightOperand) {
            return null;
        }

        @Override
        public int getPriority() {
            return BRACKET_LEVEL;
        }

        public abstract boolean isOpenning();
    }

}

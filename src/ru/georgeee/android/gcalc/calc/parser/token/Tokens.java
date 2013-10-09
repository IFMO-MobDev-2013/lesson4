package ru.georgeee.android.gcalc.calc.parser.token;

import ru.georgeee.android.gcalc.calc.exception.WrongOperandsException;
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

/**
 * Created with IntelliJ IDEA.
 * User: georgeee
 * Date: 05.10.13
 * Time: 6:34
 * To change this template use File | Settings | File Templates.
 */
public class Tokens {
    public static final int BRACKET_LEVEL = 0;
    public static final int CONST_LEVEL = 1;
    public static final int FUNCTION_LEVEL = 2;
    public static final int FACTORIAL_LEVEL = 3;
    public static final int POWER_LEVEL = 4;
    public static final int MULTIPLY_LEVEL = 5;
    public static final int NOT_LEVEL = 6;
    public static final int ADD_LEVEL = 7;
    public static final int SHIFT_LEVEL = 8;
    public static final int AND_LEVEL = 9;
    public static final int XOR_LEVEL = 10;
    public static final int OR_LEVEL = 11;

    public static final AhoTokenType MULTIPLY_TOKEN_TYPE = new AhoBothAssocTokenType() {
        @Override
        public int getPriority() {
            return MULTIPLY_LEVEL;
        }

        @Override
        public String getMatchString() {
            return "*";
        }

        @Override
        protected Expression getExpressionImpl(Expression leftOperand, Expression rightOperand) {
            return new Multiply(leftOperand, rightOperand);
        }
    };

    public static ManualTokenTypeFactory[] getManualTokenTypeFabrics(final GNumber number) {
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
                                public boolean isLeftOperandUsed() {
                                    return false;
                                }

                                @Override
                                public boolean isRightOperandUsed() {
                                    return false;
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

    public static AhoTokenType[] getAhoTokenTypes() {
        return new AhoTokenType[]{
            /*Basic operators*/
                //Unary +, Add
                new AhoAbstractTokenType() {
                    @Override
                    public int getPriority() {
                        return ADD_LEVEL;
                    }

                    @Override
                    public String getMatchString() {
                        return "+";
                    }

                    @Override
                    public Expression getExpression(Expression leftOperand, Expression rightOperand) {
                        if (rightOperand == null) {
                            throw new WrongOperandsException();
                        }
                        if (leftOperand != null) {
                            return new Add(leftOperand, rightOperand);
                        }
                        return rightOperand;
                    }

                    @Override
                    public boolean isLeftOperandUsed() {
                        return true;
                    }

                    @Override
                    public boolean isRightOperandUsed() {
                        return true;
                    }
                },
                //Unary -, Subtract
                new AhoAbstractTokenType() {
                    @Override
                    public int getPriority() {
                        return ADD_LEVEL;
                    }

                    @Override
                    public String getMatchString() {
                        return "-";
                    }

                    @Override
                    public Expression getExpression(Expression leftOperand, Expression rightOperand) {
                        if (rightOperand == null) {
                            throw new WrongOperandsException();
                        }
                        if (leftOperand != null) {
                            return new Subtract(leftOperand, rightOperand);
                        }
                        return new Negate(rightOperand);
                    }

                    @Override
                    public boolean isLeftOperandUsed() {
                        return true;
                    }

                    @Override
                    public boolean isRightOperandUsed() {
                        return true;
                    }
                },
                //Divide
                new AhoBothAssocTokenType() {
                    @Override
                    public int getPriority() {
                        return MULTIPLY_LEVEL;
                    }

                    @Override
                    public String getMatchString() {
                        return "/";
                    }

                    @Override
                    protected Expression getExpressionImpl(Expression leftOperand, Expression rightOperand) {
                        return new Divide(leftOperand, rightOperand);
                    }
                },
                //Multiply
                MULTIPLY_TOKEN_TYPE,
                //Power
                new AhoBothAssocTokenType() {
                    @Override
                    public int getPriority() {
                        return POWER_LEVEL;
                    }

                    @Override
                    public String getMatchString() {
                        return "^";
                    }

                    @Override
                    protected Expression getExpressionImpl(Expression leftOperand, Expression rightOperand) {
                        return new Power(leftOperand, rightOperand);
                    }
                },

            /*Integer operators*/
                //Mod
                new AhoBothAssocTokenType() {
                    @Override
                    public int getPriority() {
                        return MULTIPLY_LEVEL;
                    }

                    @Override
                    public String getMatchString() {
                        return "%";
                    }

                    @Override
                    protected Expression getExpressionImpl(Expression leftOperand, Expression rightOperand) {
                        return new Mod(leftOperand, rightOperand);
                    }
                },
                //And
                new AhoBothAssocTokenType() {
                    @Override
                    public int getPriority() {
                        return AND_LEVEL;
                    }

                    @Override
                    public String getMatchString() {
                        return "&";
                    }

                    @Override
                    protected Expression getExpressionImpl(Expression leftOperand, Expression rightOperand) {
                        return new And(leftOperand, rightOperand);
                    }
                },
                //Left shift
                new AhoBothAssocTokenType() {
                    @Override
                    public int getPriority() {
                        return SHIFT_LEVEL;
                    }

                    @Override
                    public String getMatchString() {
                        return "<<";
                    }

                    @Override
                    protected Expression getExpressionImpl(Expression leftOperand, Expression rightOperand) {
                        return new LShift(leftOperand, rightOperand);
                    }
                },
                //Right shift
                new AhoBothAssocTokenType() {
                    @Override
                    public int getPriority() {
                        return SHIFT_LEVEL;
                    }

                    @Override
                    public String getMatchString() {
                        return ">>";
                    }

                    @Override
                    protected Expression getExpressionImpl(Expression leftOperand, Expression rightOperand) {
                        return new RShift(leftOperand, rightOperand);
                    }
                },
                //XOR
                new AhoBothAssocTokenType() {
                    @Override
                    public int getPriority() {
                        return XOR_LEVEL;
                    }

                    @Override
                    public String getMatchString() {
                        return "XOR";
                    }

                    @Override
                    protected Expression getExpressionImpl(Expression leftOperand, Expression rightOperand) {
                        return new Xor(leftOperand, rightOperand);
                    }
                },
                //Or
                new AhoBothAssocTokenType() {
                    @Override
                    public int getPriority() {
                        return OR_LEVEL;
                    }

                    @Override
                    public String getMatchString() {
                        return "|";
                    }

                    @Override
                    protected Expression getExpressionImpl(Expression leftOperand, Expression rightOperand) {
                        return new Or(leftOperand, rightOperand);
                    }
                },
                //Factorial
                new AhoLeftAssocTokenType() {
                    @Override
                    public int getPriority() {
                        return FACTORIAL_LEVEL;
                    }

                    @Override
                    public String getMatchString() {
                        return "!";
                    }

                    @Override
                    protected Expression getExpressionImpl(Expression leftOperand) {
                        return new Factorial(leftOperand);
                    }
                },
                //Not
                new AhoRightAssocTokenType() {
                    @Override
                    public String getMatchString() {
                        return "~";
                    }

                    @Override
                    protected Expression getExpressionImpl(Expression rightOperand) {
                        return new Not(rightOperand);
                    }

                    @Override
                    public int getPriority() {
                        return NOT_LEVEL;
                    }
                },

            /*Functions*/
                //exp
                new FunctionTokenType() {
                    @Override
                    public String getMatchString() {
                        return "exp";
                    }

                    @Override
                    protected Expression getExpressionImpl(Expression rightOperand) {
                        return new Exponent(rightOperand);
                    }
                },
                //sqrt
                new FunctionTokenType() {
                    @Override
                    public String getMatchString() {
                        return "√";
                    }

                    @Override
                    protected Expression getExpressionImpl(Expression rightOperand) {
                        return new Sqrt(rightOperand);
                    }
                },
                //lg
                new FunctionTokenType() {
                    @Override
                    public String getMatchString() {
                        return "lg";
                    }

                    @Override
                    protected Expression getExpressionImpl(Expression rightOperand) {
                        return new Lg(rightOperand);
                    }
                },
                //ln
                new FunctionTokenType() {
                    @Override
                    public String getMatchString() {
                        return "ln";
                    }

                    @Override
                    protected Expression getExpressionImpl(Expression rightOperand) {
                        return new Ln(rightOperand);
                    }
                },
                //log2
                new FunctionTokenType() {
                    @Override
                    public String getMatchString() {
                        return "log2";
                    }

                    @Override
                    protected Expression getExpressionImpl(Expression rightOperand) {
                        return new Log2(rightOperand);
                    }
                },
                //acos
                new FunctionTokenType() {
                    @Override
                    public String getMatchString() {
                        return "acos";
                    }

                    @Override
                    protected Expression getExpressionImpl(Expression rightOperand) {
                        return new ACos(rightOperand);
                    }
                },
                //asin
                new FunctionTokenType() {
                    @Override
                    public String getMatchString() {
                        return "asin";
                    }

                    @Override
                    protected Expression getExpressionImpl(Expression rightOperand) {
                        return new ASin(rightOperand);
                    }
                },
                //atan
                new FunctionTokenType() {
                    @Override
                    public String getMatchString() {
                        return "atan";
                    }

                    @Override
                    protected Expression getExpressionImpl(Expression rightOperand) {
                        return new ATan(rightOperand);
                    }
                },
                //acot
                new FunctionTokenType() {
                    @Override
                    public String getMatchString() {
                        return "acot";
                    }

                    @Override
                    protected Expression getExpressionImpl(Expression rightOperand) {
                        return new ACot(rightOperand);
                    }
                },
                //sin
                new FunctionTokenType() {
                    @Override
                    public String getMatchString() {
                        return "sin";
                    }

                    @Override
                    protected Expression getExpressionImpl(Expression rightOperand) {
                        return new Sin(rightOperand);
                    }
                },
                //cos
                new FunctionTokenType() {
                    @Override
                    public String getMatchString() {
                        return "cos";
                    }

                    @Override
                    protected Expression getExpressionImpl(Expression rightOperand) {
                        return new Cos(rightOperand);
                    }
                },
                //tan
                new FunctionTokenType() {
                    @Override
                    public String getMatchString() {
                        return "tan";
                    }

                    @Override
                    protected Expression getExpressionImpl(Expression rightOperand) {
                        return new Tan(rightOperand);
                    }
                },
                //cot
                new FunctionTokenType() {
                    @Override
                    public String getMatchString() {
                        return "cot";
                    }

                    @Override
                    protected Expression getExpressionImpl(Expression rightOperand) {
                        return new Cot(rightOperand);
                    }
                },
                //acosh
                new FunctionTokenType() {
                    @Override
                    public String getMatchString() {
                        return "acosh";
                    }

                    @Override
                    protected Expression getExpressionImpl(Expression rightOperand) {
                        return new ACosh(rightOperand);
                    }
                },
                //asinh
                new FunctionTokenType() {
                    @Override
                    public String getMatchString() {
                        return "asinh";
                    }

                    @Override
                    protected Expression getExpressionImpl(Expression rightOperand) {
                        return new ASinh(rightOperand);
                    }
                },
                //atanh
                new FunctionTokenType() {
                    @Override
                    public String getMatchString() {
                        return "atanh";
                    }

                    @Override
                    protected Expression getExpressionImpl(Expression rightOperand) {
                        return new ATanh(rightOperand);
                    }
                },
                //acoth
                new FunctionTokenType() {
                    @Override
                    public String getMatchString() {
                        return "acoth";
                    }

                    @Override
                    protected Expression getExpressionImpl(Expression rightOperand) {
                        return new ACoth(rightOperand);
                    }
                },
                //sinh
                new FunctionTokenType() {
                    @Override
                    public String getMatchString() {
                        return "sinh";
                    }

                    @Override
                    protected Expression getExpressionImpl(Expression rightOperand) {
                        return new Sinh(rightOperand);
                    }
                },
                //cosh
                new FunctionTokenType() {
                    @Override
                    public String getMatchString() {
                        return "cosh";
                    }

                    @Override
                    protected Expression getExpressionImpl(Expression rightOperand) {
                        return new Cosh(rightOperand);
                    }
                },
                //tanh
                new FunctionTokenType() {
                    @Override
                    public String getMatchString() {
                        return "tanh";
                    }

                    @Override
                    protected Expression getExpressionImpl(Expression rightOperand) {
                        return new Tanh(rightOperand);
                    }
                },
                //coth
                new FunctionTokenType() {
                    @Override
                    public String getMatchString() {
                        return "coth";
                    }

                    @Override
                    protected Expression getExpressionImpl(Expression rightOperand) {
                        return new Coth(rightOperand);
                    }
                },
            /*Brackets*/
                new OpenningBracketTokenType(),
                new ClosingBracketTokenType(),

            /*Consts*/
                //Pi
                new AhoNoAssocTokenType() {
                    @Override
                    public String getMatchString() {
                        return "π";//pi
                    }

                    @Override
                    public Expression getExpression(Expression leftOperand, Expression rightOperand) {
                        return new Pi();
                    }

                    @Override
                    public int getPriority() {
                        return CONST_LEVEL;
                    }
                },
                //Pi
                new AhoNoAssocTokenType() {
                    @Override
                    public String getMatchString() {
                        return "pi";
                    }

                    @Override
                    public Expression getExpression(Expression leftOperand, Expression rightOperand) {
                        return new Pi();
                    }

                    @Override
                    public int getPriority() {
                        return CONST_LEVEL;
                    }
                },
                //e
                new AhoNoAssocTokenType() {
                    @Override
                    public String getMatchString() {
                        return "e";
                    }

                    @Override
                    public Expression getExpression(Expression leftOperand, Expression rightOperand) {
                        return new ExponentConst();
                    }

                    @Override
                    public int getPriority() {
                        return CONST_LEVEL;
                    }
                },
        };
    }

    abstract static class FunctionTokenType extends RightAssocTokenType implements AhoTokenType {
        @Override
        public int getPriority() {
            return FUNCTION_LEVEL;
        }
    }

    abstract static class AhoAbstractTokenType implements AhoTokenType {
    }

    abstract static class AhoNoAssocTokenType implements AhoTokenType {
        @Override
        public boolean isLeftOperandUsed() {
            return false;
        }

        @Override
        public boolean isRightOperandUsed() {
            return false;
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

    abstract static class BracketTokenType extends AhoNoAssocTokenType {
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

    abstract static class AhoLeftAssocTokenType extends LeftAssocTokenType implements AhoTokenType {
    }

    abstract static class AhoRightAssocTokenType extends RightAssocTokenType implements AhoTokenType {
    }

    abstract static class AhoBothAssocTokenType extends BothAssocTokenType implements AhoTokenType {
    }

}

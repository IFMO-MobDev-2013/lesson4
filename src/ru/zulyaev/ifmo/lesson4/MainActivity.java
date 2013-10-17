package ru.zulyaev.ifmo.lesson4;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v13.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.InputType;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ViewSwitcher;
import ru.zulyaev.ifmo.lesson4.parser.*;
import ru.zulyaev.ifmo.lesson4.parser.dbl.DoubleBinaryOperator;
import ru.zulyaev.ifmo.lesson4.parser.dbl.DoubleSingleArgumentFunction;
import ru.zulyaev.ifmo.lesson4.parser.dbl.DoubleUnaryOperator;
import ru.zulyaev.ifmo.lesson4.parser.exception.EvaluationException;
import ru.zulyaev.ifmo.lesson4.parser.exception.ExpressionMalformed;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * @author Никита
 */
public class MainActivity extends Activity {
    private static final String INPUT_KEY = "input";
    private static final String SELECTION_START_KEY = "start";
    private static final String SELECTION_END_KEY = "end";
    private static final String MODE_KEY = "mode";

    private static final NumberFormat NUMBER_FORMAT = DecimalFormat.getInstance(Locale.US);
    static {
        NUMBER_FORMAT.setGroupingUsed(false);
        NUMBER_FORMAT.setMinimumFractionDigits(0);
        NUMBER_FORMAT.setMaximumFractionDigits(8);
    }

    final VariableValues<Double> constants = new BasicVariableValues<>();
    ExpressionParser<Double> parser;
    private ViewSwitcher display;
    private ViewPager pager;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        constants.set(getString(R.string.pi), Math.PI)
                .set(getString(R.string.euler), Math.E)
                .set(getString(R.string.infinity), Double.POSITIVE_INFINITY)
                .set(getString(R.string.nan), Double.NaN);

        parser = new ExpressionParser.Builder<Double>()
                .setCaseSensitive(false)
                .setVariablePattern(getString(R.string.pi) + "|" +
                        getString(R.string.euler) + "|" +
                        getString(R.string.infinity)+ "|" +
                        "[a-zA-Z]+")
                .setValueParser(new ValueParser<Double>() {
                    @Override
                    public Double parse(String expression) {
                        return Double.valueOf(expression);
                    }
                })
                .setValuePattern("\\d+\\.?\\d*")

                .addOperator(1, getString(R.string.plus_sign), DoubleBinaryOperator.PLUS)
                .addOperator(1, getString(R.string.minus_sign), DoubleBinaryOperator.MINUS)
                .addOperator(2, getString(R.string.multiple_sign), DoubleBinaryOperator.TIMES)
                .addOperator(2, getString(R.string.divide_sign), DoubleBinaryOperator.DIVISION)
                .addOperator(3, getString(R.string.minus_sign), DoubleUnaryOperator.MINUS)
                .addOperator(3, getString(R.string.plus_sign), DoubleUnaryOperator.PLUS)
                .addOperator(4, getString(R.string.power_sign), DoubleBinaryOperator.POWER)
                .addOperator(5, getString(R.string.sqrt_sign), new UnaryOperator<Double>() {
                    @Override
                    public Double calc(Double operand) throws EvaluationException {
                        return Math.sqrt(operand);
                    }
                })

                .setPriorityRightAssociativity(4, true) // Set power right associative

                .addFunction(getString(R.string.sin), DoubleSingleArgumentFunction.SIN)
                .addFunction(getString(R.string.cos), DoubleSingleArgumentFunction.COS)
                .addFunction(getString(R.string.tan), DoubleSingleArgumentFunction.TAN)
                .addFunction(getString(R.string.abs), DoubleSingleArgumentFunction.ABS)
                .addFunction(getString(R.string.ln), DoubleSingleArgumentFunction.LOG)
                .addFunction(getString(R.string.log), new SingleArgumentFunction<Double>() {
                    @Override
                    public Double calc(Double value) {
                        return Math.log10(value);
                    }
                })

                .build();

        display = (ViewSwitcher) findViewById(R.id.display);
        display.setInAnimation(this, R.anim.in);
        display.setOutAnimation(this, R.anim.out);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        configEditText(getCurrentEditText());

        pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(new PagesAdapter(getFragmentManager()));
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        EditText input = getCurrentEditText();
        outState.putString(INPUT_KEY, input.getText().toString());
        outState.putInt(SELECTION_START_KEY, input.getSelectionStart());
        outState.putInt(SELECTION_END_KEY, input.getSelectionEnd());
        outState.putInt(MODE_KEY, pager.getCurrentItem());
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        EditText input = getCurrentEditText();
        input.setText(savedInstanceState.getString(INPUT_KEY));
        input.setSelection(
                savedInstanceState.getInt(SELECTION_START_KEY),
                savedInstanceState.getInt(SELECTION_END_KEY)
        );

        pager.setCurrentItem(savedInstanceState.getInt(MODE_KEY));
    }

    public void onButton(View view) {
        EditText input = getCurrentEditText();
        int start = input.getSelectionStart();
        int end = input.getSelectionEnd();
        switch (view.getId()) {
            case R.id.clear:
                if (!isInputEmpty(input)) {
                    showResult("");
                }
                break;
            case R.id.delete: {
                if (start != end) {
                    input.getText().delete(start, end);
                } else if (start > 0) {
                    input.getText().delete(start - 1, start);
                }
                break;
            }
            case R.id.equals:
                if (!isInputEmpty(input)) {
                    if ("satan".equals(input.getText().toString())) {
                        showResult("๏̯͡๏");
                        break;
                    } else try {
                        Evaluable<Double> evaluable = parser.parse(input.getText().toString());
                        Double result = evaluable.evaluate(constants);
                        if (result.isInfinite()) {
                            showResult(getString(R.string.infinity));
                        } else if (result.isNaN()) {
                            showResult(getString(R.string.nan));
                        } else {
                            showResult(NUMBER_FORMAT.format(result));
                        }
                    } catch (ExpressionMalformed | EvaluationException expressionMalformed) {
                        showResult(getString(R.string.error));
                    }
                }
                break;
            default: {
                Button button = (Button) view;
                String tag = (String)button.getTag();
                if (tag == null) tag = "";
                input.getText().replace(start, end, button.getText() + tag);
            }
        }
    }

    private boolean isInputEmpty(EditText input) {
        return input.getText().toString().trim().isEmpty();
    }

    private void showResult(String result) {
        EditText input = (EditText) display.getNextView();
        configEditText(input);
        input.setText(result);
        input.setSelection(result.length());
        display.showNext();
    }

    private EditText getCurrentEditText() {
        return (EditText) display.getCurrentView();
    }

    private void configEditText(EditText input) {
        input.setRawInputType(InputType.TYPE_CLASS_TEXT);
    }

    static class PagesAdapter extends FragmentStatePagerAdapter {
        public PagesAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            switch (i) {
                case 0: return new BasicFragment();
                case 1 : return new AdvancedFragment();
            }
            return null;
        }

        @Override
        public int getCount() {
            return 2;
        }
    }

}
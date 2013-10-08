package ru.zulyaev.ifmo.lesson4;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v13.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import ru.zulyaev.ifmo.lesson4.parser.*;
import ru.zulyaev.ifmo.lesson4.parser.dbl.DoubleBinaryOperator;
import ru.zulyaev.ifmo.lesson4.parser.dbl.DoubleSingleArgumentFunction;
import ru.zulyaev.ifmo.lesson4.parser.dbl.DoubleUnaryOperator;
import ru.zulyaev.ifmo.lesson4.parser.exception.EvaluationException;
import ru.zulyaev.ifmo.lesson4.parser.exception.ExpressionMalformed;

/**
 * @author Никита
 */
public class MainActivity extends Activity {
    private final VariableValues<Double> constants = new BasicVariableValues<>();
    private ExpressionParser<Double> parser;
    private EditText input;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        constants.set(getString(R.string.pi), Math.PI)
                .set(getString(R.string.euler), Math.E);
        parser = new ExpressionParser.Builder<Double>()
                .setCaseSensitive(false)
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

                .build();

        input = (EditText) findViewById(R.id.input);

        ViewPager pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(new PagesAdapter(getFragmentManager()));
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

    public void onButton(View view) {
        switch (view.getId()) {
            case R.id.clear:
                showResult("");
                break;
            case R.id.delete: {
                int start = input.getSelectionStart(); // Todo: check indexes
                int end = input.getSelectionEnd();
                if (start != end) {
                    input.getText().delete(start, end);
                } else {
                    input.getText().delete(start - 1, start);
                }
                break;
            }
            case R.id.equals:
                try {
                    Evaluable<Double> evaluable = parser.parse(input.getText().toString());
                    Double result = evaluable.evaluate(constants);
                    showResult(result.toString());
                } catch (ExpressionMalformed | EvaluationException expressionMalformed) {
                    showResult(getString(R.string.error));
                }
                break;
            default: {
                Button button = (Button) view;
                input.getText().append(button.getText());
            }
        }
    }

    private void showResult(String result) {
        input.setText(result); // Todo: make animated
    }
}
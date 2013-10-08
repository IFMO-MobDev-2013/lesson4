package com.polarnick.day04;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.google.common.base.Preconditions;
import com.google.common.math.DoubleMath;
import com.polarnick.polaris.math.expressionParser.ExpressionParser;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;

/**
 * Date: 16.09.13
 *
 * @author Nickolay Polyarniy aka PolarNick
 */
public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        initButtonsListeners();
    }

    private void initButtonsListeners() {
        initDigitsButtonsListeners();
        initOperationsButtons();

        findViewById(R.id.backspace).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeLastSymbol();
            }
        });
        findViewById(R.id.clear).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearInputAndResult();
            }
        });
        findViewById(R.id.bracket).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Character nextBracket = nextPossibleBracket(getExpression());
                Preconditions.checkNotNull(nextBracket, "We must block bracket button, if no bracket can be added!");
                addToInput(nextBracket.toString());
            }
        });
        findViewById(R.id.point).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToInput(".");
            }
        });
    }

    private void initOperationsButtons() {
        findViewById(R.id.plus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToInput("+");
            }
        });
        findViewById(R.id.minus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToInput("-");
            }
        });
        findViewById(R.id.multiply).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToInput("*");
            }
        });
        findViewById(R.id.divide).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToInput("/");
            }
        });
    }

    private void initDigitsButtonsListeners() {
        List<Integer> digitsButtonsIds = Arrays.asList(R.id.digit0, R.id.digit1, R.id.digit2, R.id.digit3, R.id.digit4,
                R.id.digit5, R.id.digit6, R.id.digit7, R.id.digit8, R.id.digit9);
        for (int i = 0; i < digitsButtonsIds.size(); i++) {
            final int digit = i;
            findViewById(digitsButtonsIds.get(i)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    addToInput(Integer.toString(digit));
                }
            });
        }
    }

    private Editable getInputText() {
        return ((EditText) findViewById(R.id.inputExpression)).getText();
    }

    private Editable getResultText() {
        return ((EditText) findViewById(R.id.result)).getText();
    }

    private String getExpression() {
        return getInputText().toString();
    }

    private void addToInput(String string) {
        getInputText().append(string);
        updateBracketButton(getExpression());
        updateResult();
    }

    private void removeLastSymbol() {
        if (getInputText().length() > 0) {
            getInputText().delete(getInputText().length() - 1, getInputText().length());
            updateBracketButton(getExpression());
            updateResult();
        }
    }

    private void clearInputAndResult() {
        getInputText().clear();
        getResultText().clear();
        updateBracketButton(getExpression());
    }

    private void updateBracketButton(String expression) {
        Button bracketsButton = (Button) findViewById(R.id.bracket);
        Character nextPossibleBracket = nextPossibleBracket(expression);
        if (nextPossibleBracket == null) {
            bracketsButton.setText("()");
            bracketsButton.setEnabled(false);
        } else {
            bracketsButton.setText(nextPossibleBracket.toString());
            bracketsButton.setEnabled(true);
        }
    }

    private void updateResult() {
        final String expression = getExpression();
        if (expression.isEmpty()) {
            clearInputAndResult();
        } else {
            try {
                double res = ExpressionParser.parseExpression(expression).evaluate();
                getResultText().clear();
                getResultText().append(formatResult(res));
            } catch (IllegalArgumentException e) {
                getResultText().clear();
                getResultText().append(e.getMessage());
            }
        }
    }

    private static final DecimalFormat doubleFromatter = new DecimalFormat("#.########");

    private String formatResult(double res) {
        if (DoubleMath.isMathematicalInteger(res)) {
            return Long.toString((long) res);
        } else {
            return doubleFromatter.format(res);
        }
    }

    private static final List<Character> possibleSymbolsBeforeClosingBracket =
            Arrays.asList('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', ')');

    private static final List<Character> possibleSymbolsBeforeOpeningBracket =
            Arrays.asList('+', '-', '*', '/', '(');

    private static Character nextPossibleBracket(String expression) {
        int currentLevel = 0;
        for (int i = 0; i < expression.length(); i++) {
            if (expression.charAt(i) == '(') {
                currentLevel++;
            } else if (expression.charAt(i) == ')') {
                currentLevel--;
            }
            if (currentLevel < 0) {
                return null;
            }
        }
        if (expression.isEmpty() || possibleSymbolsBeforeOpeningBracket.contains(expression.charAt(expression.length() - 1))) {
            return '(';
        } else if (possibleSymbolsBeforeClosingBracket.contains(expression.charAt(expression.length() - 1)) && currentLevel > 0) {
            return ')';
        }
        return null;
    }
}

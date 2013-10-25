package ru.mermakov.projects.MCalculator;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import junit.framework.TestCase;
import junit.framework.TestResult;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;

public class MCalcActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.main);
        TestCase test = new CalcTest();
        TestResult result = test.run();
        if (!result.wasSuccessful()) {
            ((EditText) findViewById(R.id.inputExpression)).setText("Tests failed!");
            ((EditText) findViewById(R.id.inputExpression)).setTextColor(0xFFBF00FF);
            ((EditText) findViewById(R.id.inputExpression)).setActivated(false);
        }
        initButtons();
    }

    private static final DecimalFormat doubleFromatter = new DecimalFormat("#.########");

    private String formatResult(double res) {
        return doubleFromatter.format(res);
    }

    private Editable getInput() {
        return ((EditText) findViewById(R.id.inputExpression)).getText();
    }


    private void initButtons() {
        //inti digitButtons
        List<Integer> buttonIds = Arrays.asList(R.id.digit0, R.id.digit1,
                R.id.digit2, R.id.digit3,
                R.id.digit4, R.id.digit5,
                R.id.digit6, R.id.digit7,
                R.id.digit8, R.id.digit9);
        for (int i = 0; i < 10; ++i) {
            final int tmp = i;
            findViewById(buttonIds.get(i)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((EditText) findViewById(R.id.inputExpression)).setText(getInput().append(Integer.toString(tmp)));
                }
            });
        }


        //init operators
        findViewById(R.id.plus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((EditText) findViewById(R.id.inputExpression)).setText(getInput().append("+"));
            }
        });

        findViewById(R.id.minus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((EditText) findViewById(R.id.inputExpression)).setText(getInput().append("-"));
            }
        });

        findViewById(R.id.multiply).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((EditText) findViewById(R.id.inputExpression)).setText(getInput().append("*"));
            }
        });

        findViewById(R.id.divide).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((EditText) findViewById(R.id.inputExpression)).setText(getInput().append("/"));
            }
        });

        findViewById(R.id.bracketOpen).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((EditText) findViewById(R.id.inputExpression)).setText(getInput().append("("));
            }
        });

        findViewById(R.id.bracketClose).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((EditText) findViewById(R.id.inputExpression)).setText(getInput().append(")"));
            }
        });

        findViewById(R.id.point).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((EditText) findViewById(R.id.inputExpression)).setText(getInput().append("."));
            }
        });

        findViewById(R.id.clear).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((EditText) findViewById(R.id.inputExpression)).setText("");
                ((TextView) findViewById(R.id.result)).setText("");
            }
        });

        findViewById(R.id.backspace).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getInput().length() != 0) {
                    ((EditText) findViewById(R.id.inputExpression))
                            .setText(getInput().delete(getInput().length() - 1, getInput().length()));
                }
            }
        });

        findViewById(R.id.exec).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Expression = ((EditText) findViewById(R.id.inputExpression)).getText().toString();
                Parser parser = new Parser();
                try {
                    double res = parser.evaluate(Expression);
                    ((TextView) findViewById(R.id.result)).setText(Double.toString(res));
                } catch (ParserException exx) {
                    AlertDialog alertDialog;
                    alertDialog = new AlertDialog.Builder(MCalcActivity.this).create();
                    alertDialog.setTitle("Error");
                    alertDialog.setMessage(exx.toString());
                    alertDialog.show();
                }
            }
        });
    }
}

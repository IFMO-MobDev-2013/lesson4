package ru.ifmo.ctddev.koval.calculator;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import net.sourceforge.jeval.EvaluationException;
import net.sourceforge.jeval.Evaluator;

public class CalculatorActivity extends Activity {

    private EditText input;
    private TextView result;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        input = (EditText) findViewById(R.id.input);
        result = (TextView) findViewById(R.id.result);

        input.setKeyListener(null);
    }

    public void oneListener(View view) {
        input.setText(input.getText() + "1");
        input.setSelection(input.getText().length());
    }

    public void twoListener(View view) {
        input.setText(input.getText() + "2");
        input.setSelection(input.getText().length());
    }

    public void threeListener(View view) {
        input.setText(input.getText() + "3");
        input.setSelection(input.getText().length());
    }

    public void fourListener(View view) {
        input.setText(input.getText() + "4");
        input.setSelection(input.getText().length());
    }

    public void fiveListener(View view) {
        input.setText(input.getText() + "5");
        input.setSelection(input.getText().length());
    }

    public void sixListener(View view) {
        input.setText(input.getText() + "6");
        input.setSelection(input.getText().length());
    }

    public void sevenListener(View view) {
        input.setText(input.getText() + "7");
        input.setSelection(input.getText().length());
    }

    public void eightListener(View view) {
        input.setText(input.getText() + "8");
        input.setSelection(input.getText().length());
    }

    public void nineListener(View view) {
        input.setText(input.getText() + "9");
        input.setSelection(input.getText().length());
    }

    public void zeroListener(View view) {
        input.setText(input.getText() + "0");
        input.setSelection(input.getText().length());
    }

    public void plusListener(View view) {
        input.setText(input.getText() + "+");
        input.setSelection(input.getText().length());
    }

    public void substractListener(View view) {
        input.setText(input.getText() + "-");
        input.setSelection(input.getText().length());
    }

    public void divisionListener(View view) {
        input.setText(input.getText() + "/");
        input.setSelection(input.getText().length());
    }

    public void timesListener(View view) {
        input.setText(input.getText() + "*");
        input.setSelection(input.getText().length());
    }

    public void pointListener(View view) {
        input.setText(input.getText() + ".");
        input.setSelection(input.getText().length());
    }


    public void cListener(View view) {
        input.setText("");
        result.setText("0");
        input.setSelection(input.getText().length());
    }

    public void openBracketListener(View view) {
        input.setText(input.getText() + "(");
        input.setSelection(input.getText().length());
    }

    public void closeBracketListener(View view) {
        input.setText(input.getText() + ")");
        input.setSelection(input.getText().length());
    }

    public void backspaceListener(View view) {
        String currentText = input.getText().toString();
        if (!currentText.equals("")) {
            input.setText(currentText.substring(0, currentText.length() - 1));
        }
        input.setSelection(input.getText().length());
    }

    public void spaceListener(View view) {
        input.setText(input.getText() + " ");
        input.setSelection(input.getText().length());
    }

    public void countListener(View view) {
        if (input.getText().toString().isEmpty()) {
            result.setText("0");
            return;
        }
        try {
            String res = new Evaluator().evaluate(input.getText().toString());
            result.setText(res);
        } catch (EvaluationException e) {
            result.setText("Invalid expression");
        }
    }


}

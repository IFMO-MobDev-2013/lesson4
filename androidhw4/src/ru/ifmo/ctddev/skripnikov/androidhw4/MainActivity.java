package ru.ifmo.ctddev.skripnikov.androidhw4;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
    private TextView textView1;
    private String expression = "";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        textView1 = (TextView) findViewById(R.id.text_view1);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("expression", expression);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        expression = savedInstanceState.getString("expression");
        textView1.setText(expression);
    }

    public void onClickDeleteAll(View v) {
        expression = "";
        textView1.setText(expression);
    }

    public void onClickDelete(View v) {
        if (expression.length() > 0) {
            expression = expression.substring(0, expression.length() - 1);
            textView1.setText(expression);
        }
    }

    public void onClickCalculate(View v) {
        try {
            Evaluable e = Parser.parse(expression);
            Double d = e.evaluate();
            if (d.isInfinite()) {
                Toast.makeText(getApplicationContext(),
                        R.string.error_infinite,
                        Toast.LENGTH_SHORT).show();
            } else {
                expression = d.toString();
                textView1.setText(expression);
            }
        } catch (ParseException e1) {
            Toast.makeText(getApplicationContext(),
                    R.string.error_invalid_expression,
                    Toast.LENGTH_SHORT).show();
        } catch (EvaluateException e1) {
            Toast.makeText(getApplicationContext(),
                    R.string.error_div_by_zero,
                    Toast.LENGTH_SHORT).show();
        }
    }

    public void onClickSimple(View v) {
        expression += ((Button) v).getText().toString();
        textView1.setText(expression);
    }
}

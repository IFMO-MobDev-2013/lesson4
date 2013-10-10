package ru.skipor.androidLecture4;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import ru.skipor.androidLecture4.Calculator.Calculator;

public class MainActivity extends Activity {


    private TextView display;
    private Calculator calculator;

    private void updateDisplay(String text) {
        display.setText(text);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        display = (TextView) findViewById(R.id.display);
        calculator = new Calculator();


//        display.setText("20*3-4");  /////

    }


    public void operation_onClick(View view) {
        String buttonToken = String.valueOf(((Button) view).getText());

        updateDisplay(
                calculator.addOperation(buttonToken)
        );


    }

    public void bracket_onClick(View view) {
        updateDisplay(
                calculator.addBracket()
        );
    }

    public void clear_onClick(View view) {
        updateDisplay(
                calculator.clear()
        );

    }

    public void allClear_onClick(View view) {
        updateDisplay(
                calculator.clearAll()
        );
    }

    public void dot_onClick(View view) {
        updateDisplay(
                calculator.addDot()
        );

    }

    public void evaluate_onClick(View view) {
        updateDisplay(
                calculator.evaluate()
        );
    }

    public void digit_onClick(View view) {

        String buttonToken = String.valueOf(((Button) view).getText());

        updateDisplay(
                calculator.addDigit(buttonToken)
        );

    }
}

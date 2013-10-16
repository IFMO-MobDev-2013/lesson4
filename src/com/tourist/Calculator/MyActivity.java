package com.tourist.Calculator;

import android.app.Activity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MyActivity extends Activity {

    TextView text;
    TextView result;
    boolean evaluated = false;
    boolean error = false;

    public String evaluate(String expression) throws ParserException {
        Expression expr = ExpressionParser.parse(expression);
        String value = "" + expr.evaluate();
        if (value.contains("N") || value.contains("I")) {
            value = "Division by zero";
            error = true;
        }
        if (value.endsWith(".0")) {
            value = value.substring(0, value.length() - 2);
        }
        return value;
    }

    public void pushButton(String com) {
        String sText = text.getText().toString();
        String sResult = result.getText().toString();
        switch (com.charAt(0)) {
            case 'C': {
                text.setText("");
                result.setText("");
                evaluated = false;
                error = false;
                break;
            }
            case '<': {
                if (evaluated) {
                    if (error) {
                        error = false;
                    } else {
                        text.setText(sResult.substring(1, sResult.length()));
                    }
                    result.setText("");
                    evaluated = false;
                } else
                if (sText.length() != 0) {
                    text.setText(sText.substring(0, sText.length() - 1));
                }
                break;
            }
            case '=': {
                if (sText.length() > 0) {
                    try {
                        result.setText("=" + evaluate(sText));
                    } catch (ParserException e) {
                        result.setText("=" + e.getMessage());
                        error = true;
                    }
                    evaluated = true;
                }
                break;
            }
            default: {
                if (evaluated) {
                    if (error) {
                        text.setText(sText + com);
                        error = false;
                    } else
                    if ("+-*/".indexOf(com.charAt(0)) != -1){
                        text.setText(sResult.substring(1, sResult.length()) + com);
                    } else {
                        text.setText(com);
                    }
                    result.setText("");
                    evaluated = false;
                } else
                if (sText.length() == 100) {
                    Toast toast = Toast.makeText(this, "Expression length can't exceed 100 characters", Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    text.setText(sText + com);
                }
            }
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout main = new LinearLayout(this);
        main.setOrientation(LinearLayout.VERTICAL);
        text = new TextView(this);
        text.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        text.setGravity(Gravity.RIGHT);
        text.setTextSize(TypedValue.COMPLEX_UNIT_SP, 21);
        main.addView(text);
        result = new TextView(this);
        result.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0, 1));
        result.setGravity(Gravity.RIGHT);
        result.setTextSize(TypedValue.COMPLEX_UNIT_SP, 21);
        main.addView(result);
        String buttonChars = "C/*<789-456+123.0()=";
        for (int row = 0; row < 5; row++) {
            LinearLayout aux = new LinearLayout(this);
            aux.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            aux.setGravity(Gravity.BOTTOM);
            for (int col = 0; col < 4; col++) {
                Button button = new Button(this);
                String buttonText = "" + buttonChars.charAt(row * 4 + col);
                if ("<".equals(buttonText)) {
                    buttonText = "<=";
                }
                button.setText(buttonText);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        pushButton(((Button) view).getText().toString());
                    }
                });
                button.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1));
                aux.addView(button);
            }
            main.addView(aux);
        }
        setContentView(main);
    }
}

package com.blumonk.Calc;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.blumonk.Calc.Parser.Evaluable;
import com.blumonk.Calc.Parser.Parser;

public class CalcActivity extends Activity {

    public TextView text;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        text = (TextView) findViewById(R.id.editText);
        findViewById(R.id.zero).setOnClickListener(pushSymbol);
        findViewById(R.id.one).setOnClickListener(pushSymbol);
        findViewById(R.id.two).setOnClickListener(pushSymbol);
        findViewById(R.id.three).setOnClickListener(pushSymbol);
        findViewById(R.id.four).setOnClickListener(pushSymbol);
        findViewById(R.id.five).setOnClickListener(pushSymbol);
        findViewById(R.id.six).setOnClickListener(pushSymbol);
        findViewById(R.id.seven).setOnClickListener(pushSymbol);
        findViewById(R.id.eight).setOnClickListener(pushSymbol);
        findViewById(R.id.nine).setOnClickListener(pushSymbol);
        findViewById(R.id.plus).setOnClickListener(pushSymbol);
        findViewById(R.id.minus).setOnClickListener(pushSymbol);
        findViewById(R.id.division).setOnClickListener(pushSymbol);
        findViewById(R.id.times).setOnClickListener(pushSymbol);
        findViewById(R.id.dot).setOnClickListener(addDot);
        findViewById(R.id.open).setOnClickListener(pushSymbol);
        findViewById(R.id.close).setOnClickListener(closeParentheses);
        findViewById(R.id.delete).setOnClickListener(deleteSymbol);
        findViewById(R.id.clear).setOnClickListener(clearAll);
        findViewById(R.id.equals).setOnClickListener(updateScreen);
    }

    boolean isOperation(char c) {
        return c == '+' || c == '-' || c == '/' || c == '*';
    }

    Button.OnClickListener addDot = new Button.OnClickListener() {
        @Override
        public void onClick(View view) {
            String s = text.getText().toString();
            if (s.isEmpty()) {
                text.setText("0.");
                return;
            }
            char last = s.charAt(s.length() - 1);
            if (last == '.') {
                return;
            }
            if (Character.isDigit(last)) {
                int i = s.length() - 1;
                while (Character.isDigit(s.charAt(i))) {
                    --i;
                }
                if (i >= 0 && s.charAt(i) == '.')
                    return;
            }
            if (last == '(' || isOperation(last)) {
                text.setText(s + "0.");
            } else {
                text.setText(s + '.');
            }
        }
    };

    Button.OnClickListener closeParentheses = new Button.OnClickListener() {
        @Override
        public void onClick(View view) {
            String s = text.getText().toString();
            if (s.isEmpty() || s.charAt(s.length() - 1) == '(') {
                return;
            } else {
                text.setText(s + ')');
            }
        }
    };

    Button.OnClickListener pushSymbol = new Button.OnClickListener() {
        @Override
        public void onClick(View view) {
            CharSequence symbol = ((Button) view).getText();
            text.setText(text.getText().toString() + symbol);
        }
    };

    Button.OnClickListener deleteSymbol = new Button.OnClickListener() {
        @Override
        public void onClick(View view) {
            String curr = text.getText().toString();
            if (curr.length() == 0) {
                return;
            }
            text.setText(curr.substring(0, curr.length() - 1));
        }
    };

    Button.OnClickListener clearAll = new Button.OnClickListener() {
        @Override
        public void onClick(View view) {
            text.setText("");
        }
    };

    Button.OnClickListener updateScreen = new Button.OnClickListener() {
        @Override
        public void onClick(View view) {
            try {
                Evaluable eval = Parser.parse(text.getText().toString());
                double res = eval.evaluate();
                text.setText(Double.toString(res));
            } catch (Exception e) {
                Log.d("error", "error", e);
                text.setText("Invalid input");
            }
        }
    };


}

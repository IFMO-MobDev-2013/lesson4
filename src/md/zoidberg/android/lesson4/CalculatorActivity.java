package md.zoidberg.android.lesson4;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import md.zoidberg.java.evaluator.EvaluationException;
import md.zoidberg.java.evaluator.Evaluator;

import java.util.HashMap;
import java.util.Map;

public class CalculatorActivity extends Activity {
    public static final String EXPRESSION_TAG = "expression";
    /**
     * Called when the activity is first created.
     */
    String expression;
    String errorMessage;
    boolean isError = false;
    boolean toErase = false;
    TextView calcText;
    private static final Map<Integer, String> suffixes;
    static {
        suffixes = new HashMap<Integer, String>();
        suffixes.put(R.id.btn0, "0");
        suffixes.put(R.id.btn1, "1");
        suffixes.put(R.id.btn2, "2");
        suffixes.put(R.id.btn3, "3");
        suffixes.put(R.id.btn4, "4");
        suffixes.put(R.id.btn5, "5");
        suffixes.put(R.id.btn6, "6");
        suffixes.put(R.id.btn7, "7");
        suffixes.put(R.id.btn8, "8");
        suffixes.put(R.id.btn9, "9");

        suffixes.put(R.id.btnPlus, "+");
        suffixes.put(R.id.btnMinus, "-");
        suffixes.put(R.id.btnMult, "*");
        suffixes.put(R.id.btnDivide, "/");

        suffixes.put(R.id.btnDot, ".");

        suffixes.put(R.id.btnBkOp, "(");
        suffixes.put(R.id.btnBkCl, ")");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        calcText = (TextView)findViewById(R.id.calcText);

        if (savedInstanceState != null && savedInstanceState.getString(EXPRESSION_TAG) != null) {
            expression = savedInstanceState.getString(EXPRESSION_TAG);
            sync();
        } else {
            expression = "";
        }
    }

    private void appendString(String s) {
        if (isError || toErase) erase();
        expression = expression + s;
        sync();
    }

    private void erase() {
        expression = "";
        isError = false;
        toErase = false;
        sync();
    }

    public void onButtonPress(View view) {
        if (suffixes.containsKey(view.getId())) {
            appendString(suffixes.get(view.getId()));
            return;
        }

        switch (view.getId()) {
            case R.id.btnBksp:
                backspace();
                break;

            case R.id.btnC:
                erase();
                break;

            case R.id.btnEq:
                solve();
                break;
        }
    }

    private void solve() {
        try {
            expression = new Evaluator().evaluate(expression);
        } catch (EvaluationException e) {
            isError = true;
            errorMessage = "Invalid";
            Log.d("calcException", e.getMessage());
        } catch (ArithmeticException e) {
            isError = true;
            errorMessage = "Division by zero";
            Log.d("calcException", e.getMessage());
        }

        sync();
    }

    private void sync() {
        if (isError) {
            calcText.setText(errorMessage);
        } else {
            calcText.setText(expression);
        }
    }

    private void backspace() {
        if (isError) erase();
        if (expression.length() == 0) return;
        expression = expression.substring(0, expression.length() - 1);
        sync();
    }

    @Override
    public void onSaveInstanceState(Bundle state) {
        state.putString(EXPRESSION_TAG, expression);
    }
}

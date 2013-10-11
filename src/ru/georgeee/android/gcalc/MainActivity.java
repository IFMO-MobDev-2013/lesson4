package ru.georgeee.android.gcalc;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.Layout;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import ru.georgeee.android.gcalc.calc.Evaluator;
import ru.georgeee.android.gcalc.calc.number.GNumber;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;

public class MainActivity extends Activity implements View.OnClickListener {
    final static boolean INT_MODE = false;
    final static boolean REAL_MODE = true;
    final static int INT_FUNCTION_SET = 0;
    final static int EXP_FUNCTION_SET = 1;
    final static int TRIG_FUNCTION_SET = 2;
    final static int ATRIG_FUNCTION_SET = 3;
    final static int HYP_FUNCTION_SET = 4;
    final static int AHYP_FUNCTION_SET = 5;
    boolean inputHasErrorState;
    boolean mode;
    int functionSet;
    EditText expressionInput;
    Deque<String> evaluatedExpressions;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        String[] exprs = new String[evaluatedExpressions.size()];
        outState.putStringArray("evaluatedExpressions", evaluatedExpressions.toArray(exprs));
        outState.putBoolean("mode", mode);
        outState.putInt("functionSet", functionSet);
        outState.putBoolean("inputHasErrorState", inputHasErrorState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        restore(savedInstanceState);
    }

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        restore(savedInstanceState);
    }

    protected void restore(Bundle savedInstanceState) {
        if (savedInstanceState == null) savedInstanceState = new Bundle();
        mode = savedInstanceState.containsKey("mode") ? savedInstanceState.getBoolean("mode") : REAL_MODE;
        functionSet = savedInstanceState.containsKey("functionSet") ? savedInstanceState.getInt("functionSet") : EXP_FUNCTION_SET;
        inputHasErrorState = savedInstanceState.containsKey("inputHasErrorState") ? savedInstanceState.getBoolean("inputHasErrorState") : false;
        evaluatedExpressions = new ArrayDeque<String>();
        if (savedInstanceState.containsKey("evaluatedExpressions")) {
            String[] exprs = savedInstanceState.getStringArray("evaluatedExpressions");
            Collections.addAll(evaluatedExpressions, exprs);
        }
        setOnclickListeners();
        expressionInput = (EditText) findViewById(R.id.expressionInput);
        View.OnTouchListener otl = new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        Layout layout = ((EditText) v).getLayout();
                        float x = event.getX() + expressionInput.getScrollX();
                        int offset = layout.getOffsetForHorizontal(0, x);
                        if (offset > 0)
                            if (x > layout.getLineMax(0))
                                expressionInput.setSelection(offset);     // touch was at end of text
                            else
                                expressionInput.setSelection(offset - 1);
                        break;
                }
                return true;
            }
        };
        expressionInput.setOnTouchListener(otl);
        setInputErrorStatus(inputHasErrorState);
        setDependentLabels();
    }

    public void setDependentLabels() {
        Button btn;
        for (int i = 0; i <= 9; ++i) {
            btn = getBtn(i);
            if (btn != null) {
                if (i > 0 && i < 4)
                    if (mode == INT_MODE) {
                        btn.setTextColor(getResources().getColor(R.color.btn_color));
                    } else if (mode == REAL_MODE) {
                        btn.setTextColor(getResources().getColor(R.color.system_btn_color));
                    }
                btn.setText(getLabel("btn" + i));
            }
        }
        int active_fset_btn_index = -1;
        if (mode == REAL_MODE)
            if (functionSet == EXP_FUNCTION_SET)
                active_fset_btn_index = 1;
            else if (functionSet == TRIG_FUNCTION_SET || functionSet == ATRIG_FUNCTION_SET)
                active_fset_btn_index = 2;
            else if (functionSet == HYP_FUNCTION_SET || functionSet == AHYP_FUNCTION_SET)
                active_fset_btn_index = 3;
        if (active_fset_btn_index != -1 && (btn = getBtn(active_fset_btn_index)) != null)
            btn.setTextColor(getResources().getColor(R.color.system_btn_color_active));
    }

    public String getLabel(String id) {
        String result = getStringResourceByName(id);
        if (result != null) return result;
        String idWithMode = id + "_" + (mode == INT_MODE ? "int" : "real");
        result = getStringResourceByName(idWithMode);
        if (result != null) return result;
        String functionSetName = null;
        switch (functionSet) {
            case EXP_FUNCTION_SET:
                functionSetName = "exp";
                break;
            case TRIG_FUNCTION_SET:
                functionSetName = "trig";
                break;
            case ATRIG_FUNCTION_SET:
                functionSetName = "atrig";
                break;
            case HYP_FUNCTION_SET:
                functionSetName = "hyp";
                break;
            case AHYP_FUNCTION_SET:
                functionSetName = "ahyp";
                break;
        }
        if (functionSetName != null) result = getStringResourceByName(idWithMode + "_" + functionSetName);
        return result;
    }

    private String getStringResourceByName(String aString) {
        int resId = getResources().getIdentifier(aString, "string", getPackageName());
        String result = null;
        try {
            result = getString(resId);
        } catch (Resources.NotFoundException ex) {
        }
        return result;
    }

    protected Button getBtn(int i) {
        int resId = getResources().getIdentifier("btn" + i, "id", getPackageName());
        Button result = null;
        try {
            result = (Button) findViewById(resId);
        } catch (Resources.NotFoundException ex) {
        }
        return result;
    }

    protected void addToExpression(String text) {
        int sel = expressionInput.getSelectionStart();
        if (sel == -1) {
            expressionInput.append(text);
        } else {
            expressionInput.getText().insert(sel, text);
        }
    }

    protected void setInputErrorStatus(boolean hasError) {
        if (hasError) {
            expressionInput.setBackgroundColor(getResources().getColor(R.color.expression_input_error));
        } else {
            expressionInput.setBackgroundColor(getResources().getColor(R.color.expression_input_ok));
        }
        inputHasErrorState = hasError;
    }

    protected void setOnclickListeners() {
        findViewById(R.id.btn0).setOnClickListener(this);
        findViewById(R.id.btn1).setOnClickListener(this);
        findViewById(R.id.btn2).setOnClickListener(this);
        findViewById(R.id.btn3).setOnClickListener(this);
        findViewById(R.id.btn4).setOnClickListener(this);
        findViewById(R.id.btn5).setOnClickListener(this);
        findViewById(R.id.btn6).setOnClickListener(this);
        findViewById(R.id.btn7).setOnClickListener(this);
        findViewById(R.id.btn8).setOnClickListener(this);
        findViewById(R.id.btn9).setOnClickListener(this);
        findViewById(R.id.num0).setOnClickListener(this);
        findViewById(R.id.num1).setOnClickListener(this);
        findViewById(R.id.num2).setOnClickListener(this);
        findViewById(R.id.num3).setOnClickListener(this);
        findViewById(R.id.num4).setOnClickListener(this);
        findViewById(R.id.num5).setOnClickListener(this);
        findViewById(R.id.num6).setOnClickListener(this);
        findViewById(R.id.num7).setOnClickListener(this);
        findViewById(R.id.num8).setOnClickListener(this);
        findViewById(R.id.num9).setOnClickListener(this);
        findViewById(R.id.add).setOnClickListener(this);
        findViewById(R.id.subtract).setOnClickListener(this);
        findViewById(R.id.multiply).setOnClickListener(this);
        findViewById(R.id.division).setOnClickListener(this);
        findViewById(R.id.delete).setOnClickListener(this);
        findViewById(R.id.clear).setOnClickListener(this);
        findViewById(R.id.historyBtn).setOnClickListener(this);
        findViewById(R.id.equal).setOnClickListener(this);
        findViewById(R.id.leftBracket).setOnClickListener(this);
        findViewById(R.id.rightBracket).setOnClickListener(this);
        findViewById(R.id.point).setOnClickListener(this);
        findViewById(R.id.power).setOnClickListener(this);
    }

    protected GNumber evaluate(String expression) {
        GNumber result = null;
        try {
            if (mode == INT_MODE)
                result = Evaluator.evaluateBigInt(expression);
            else if (mode == REAL_MODE)
                result = Evaluator.evaluateDouble(expression);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (result != null) evaluatedExpressions.addLast(expression);
        return result;
    }

    protected String getExpressionFromStack() {
        if (evaluatedExpressions.isEmpty()) return null;
        return evaluatedExpressions.removeLast();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.add:
                addToExpression("+");
                break;
            case R.id.subtract:
                addToExpression("-");
                break;
            case R.id.multiply:
                addToExpression("*");
                break;
            case R.id.division:
                addToExpression("/");
                break;
            case R.id.num0:
                addToExpression("0");
                break;
            case R.id.num1:
                addToExpression("1");
                break;
            case R.id.num2:
                addToExpression("2");
                break;
            case R.id.num3:
                addToExpression("3");
                break;
            case R.id.num4:
                addToExpression("4");
                break;
            case R.id.num5:
                addToExpression("5");
                break;
            case R.id.num6:
                addToExpression("6");
                break;
            case R.id.num7:
                addToExpression("7");
                break;
            case R.id.num8:
                addToExpression("8");
                break;
            case R.id.num9:
                addToExpression("9");
                break;
            case R.id.point:
                addToExpression(".");
                break;
            case R.id.power:
                addToExpression("^");
                break;
            case R.id.leftBracket:
                addToExpression("(");
                break;
            case R.id.rightBracket:
                addToExpression(")");
                break;
            case R.id.delete:
                int selStart = expressionInput.getSelectionStart();
                int selEnd = expressionInput.getSelectionEnd();
                if (selStart != selEnd) {
                    expressionInput.getText().delete(selStart, selEnd);
                } else if (selStart > 0) {
                    expressionInput.getText().delete(selStart - 1, selStart);
                }
                if (expressionInput.getText().length() == 0) setInputErrorStatus(false);
                break;
            case R.id.clear:
                expressionInput.getText().clear();
                setInputErrorStatus(false);
                break;
            case R.id.equal:
                String expression = expressionInput.getText().toString();
                GNumber result = evaluate(expression);
                setInputErrorStatus(result == null);
                if (result != null) {
                    expressionInput.getText().clear();
                    addToExpression(result.toString());
                }
                break;
            case R.id.historyBtn:
                String expressionFromStack = getExpressionFromStack();
                if (expressionFromStack != null) {
                    setInputErrorStatus(false);
                    expressionInput.getText().clear();
                    addToExpression(expressionFromStack);
                }
                break;
            case R.id.btn0:
                if (mode == REAL_MODE) {
                    mode = INT_MODE;
                    functionSet = INT_FUNCTION_SET;
                    findViewById(R.id.point).setEnabled(false);
                    findViewById(R.id.btn3).setEnabled(false);
                } else if (mode == INT_MODE) {
                    mode = REAL_MODE;
                    functionSet = EXP_FUNCTION_SET;
                    findViewById(R.id.point).setEnabled(true);
                    findViewById(R.id.btn3).setEnabled(true);
                }
                break;
            case R.id.btn1:
                if (mode == REAL_MODE) {
                    functionSet = EXP_FUNCTION_SET;
                } else if (mode == INT_MODE) {
                    addToExpression("&");
                }
                break;
            case R.id.btn2:
                if (mode == REAL_MODE) {
                    if (functionSet == TRIG_FUNCTION_SET)
                        functionSet = ATRIG_FUNCTION_SET;
                    else functionSet = TRIG_FUNCTION_SET;
                } else if (mode == INT_MODE) {
                    addToExpression("%");
                }
                break;
            case R.id.btn3:
                if (mode == REAL_MODE) {
                    if (functionSet == HYP_FUNCTION_SET)
                        functionSet = AHYP_FUNCTION_SET;
                    else functionSet = HYP_FUNCTION_SET;
                } else if (mode == INT_MODE) {
                }
                break;
            case R.id.btn4:
                switch (functionSet) {
                    case EXP_FUNCTION_SET:
                        addToExpression("exp ");
                        break;
                    case TRIG_FUNCTION_SET:
                        addToExpression("sin ");
                        break;
                    case ATRIG_FUNCTION_SET:
                        addToExpression("asin ");
                        break;
                    case HYP_FUNCTION_SET:
                        addToExpression("sinh ");
                        break;
                    case AHYP_FUNCTION_SET:
                        addToExpression("asinh ");
                        break;
                    case INT_FUNCTION_SET:
                        addToExpression("~");
                        break;
                }
                break;
            case R.id.btn5:
                switch (functionSet) {
                    case EXP_FUNCTION_SET:
                        addToExpression("lg ");
                        break;
                    case TRIG_FUNCTION_SET:
                        addToExpression("tan ");
                        break;
                    case ATRIG_FUNCTION_SET:
                        addToExpression("atan ");
                        break;
                    case HYP_FUNCTION_SET:
                        addToExpression("tanh ");
                        break;
                    case AHYP_FUNCTION_SET:
                        addToExpression("atanh ");
                        break;
                    case INT_FUNCTION_SET:
                        addToExpression("|");
                        break;
                }
                break;
            case R.id.btn6:
                switch (functionSet) {
                    case EXP_FUNCTION_SET:
                        addToExpression("e");
                        break;
                    case TRIG_FUNCTION_SET:
                        addToExpression("π");
                        break;
                    case ATRIG_FUNCTION_SET:
                        addToExpression("π");
                        break;
                    case HYP_FUNCTION_SET:
                        addToExpression("e");
                        break;
                    case AHYP_FUNCTION_SET:
                        addToExpression("e");
                        break;
                    case INT_FUNCTION_SET:
                        addToExpression(">>");
                        break;
                }
                break;
            case R.id.btn7:
                switch (functionSet) {
                    case EXP_FUNCTION_SET:
                        addToExpression("ln ");
                        break;
                    case TRIG_FUNCTION_SET:
                        addToExpression("cos ");
                        break;
                    case ATRIG_FUNCTION_SET:
                        addToExpression("acos ");
                        break;
                    case HYP_FUNCTION_SET:
                        addToExpression("cosh ");
                        break;
                    case AHYP_FUNCTION_SET:
                        addToExpression("acosh ");
                        break;
                    case INT_FUNCTION_SET:
                        addToExpression("!");
                        break;
                }
                break;
            case R.id.btn8:
                switch (functionSet) {
                    case EXP_FUNCTION_SET:
                        addToExpression("log2 ");
                        break;
                    case TRIG_FUNCTION_SET:
                        addToExpression("cot ");
                        break;
                    case ATRIG_FUNCTION_SET:
                        addToExpression("acot ");
                        break;
                    case HYP_FUNCTION_SET:
                        addToExpression("coth ");
                        break;
                    case AHYP_FUNCTION_SET:
                        addToExpression("acoth ");
                        break;
                    case INT_FUNCTION_SET:
                        addToExpression(" XOR ");
                        break;
                }
                break;
            case R.id.btn9:
                if (mode == REAL_MODE) addToExpression("√");
                else if (mode == INT_MODE) addToExpression("<<");
                break;
        }
        setDependentLabels();
    }
}

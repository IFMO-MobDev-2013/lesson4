package ru.marsermd.Swipylator;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import ru.marsermd.Swipylator.GUI.Runner;
import ru.marsermd.Swipylator.GUI.SwipeView;
import ru.marsermd.Swipylator.GUI.SwipeViewSetter;
import ru.marsermd.Swipylator.core.BigIntegerBinaryOperator;
import ru.marsermd.Swipylator.core.ExpressionParserBuilder;
import ru.marsermd.Swipylator.core.Operand;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class SwipeActivity extends Activity {
    /**
     * Called when the activity is first created.
     */

    private Map<String, Double> doubleValues;
    private Map<String, BigInteger> bigIntValues;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        init();
        doubleValues = new HashMap();
        doubleValues.put("π", Math.PI);
        doubleValues.put("e", Math.E);
        bigIntValues = new HashMap();
        bigIntValues.put("π", BigInteger.valueOf((int) Math.PI));
        bigIntValues.put("e", BigInteger.valueOf((int)Math.E));

    }

    private EditText input;
    private TextView result;
    private void init() {
        setInputFieldSettings();
        result = (TextView)findViewById(R.id.result);

        int[] ids = {
                R.id.b_equals,
                R.id.b7, R.id.b8, R.id.b9, R.id.b_multiply, R.id.bC,
                R.id.b4, R.id.b5, R.id.b6, R.id.b_divide, R.id.b_erase,
                R.id.b1, R.id.b2, R.id.b3, R.id.b_add, R.id.b_brackets,
                R.id.b_left, R.id.b0, R.id.b_right, R.id.b_substract, R.id.b_dot
        };
        String[] middles = {
                "=",
                "7", "8", "9", " * ", "C",
                "4", "5", "6", " / ", "<-",
                "1", "2", "3", " + ", "()",
                "<", "0", ">", " - ", "."
        };
        String[] tops = {
                "~",
                "", "", "π", "", "",
                "", "", "", " % ", "del",
                "", "", "", "", "(",
                "<<", "00", ">>", "", ""
        };
        String[] bots = {
                "",
                "", "", "e", "", "",
                "", "", "", "", "",
                "", "", "", "", ")",
                "", "000", "", "", ""
        };

        setupSwipeButtons(ids, middles, tops, bots);

    }

    private void setupSwipeButtons(final int[] ids, final String[] middles, final String[] tops, final String[] bots) {
        for (int i = 0; i < ids.length; i++) {
            SwipeViewSetter svSetter = new SwipeViewSetter(this, ids[i],
                    tops[i], middles[i], bots[i]) {
                @Override
                protected void setterExecute(String s) {
                    int start = input.getSelectionStart();
                    int end = input.getSelectionEnd();
                    String text = input.getText().toString();
                    text = text.substring(0, start) + s + text.substring(end, text.length());
                    input.setText(text);
                    input.setSelection(start + s.length());
                }
            };
        }

        SwipeView tmp = (SwipeView)findViewById(R.id.bC);
        tmp.middleRunner = new Runner() {
            @Override
            public void execute() {
                input.getText().clear();
            }
        };

        tmp = (SwipeView)findViewById(R.id.b_erase);
        tmp.middleRunner = new Runner() {
            @Override
            public void execute() {
                int pos = input.getSelectionStart();
                if (pos == 0)
                    return;
                String text = input.getText().toString();
                text = text.substring(0, pos - 1) + text.substring(pos, text.length());
                input.setText(text);
                input.setSelection(pos - 1);
            }
        };

        tmp.topRunner = new Runner() {
            @Override
            public void execute() {
                int pos = input.getSelectionStart();
                if (pos == input.length())
                    return;
                String text = input.getText().toString();
                text = text.substring(0, pos) + text.substring(pos + 1, text.length());
                input.setText(text);
                input.setSelection(pos);
            }
        };

        tmp = (SwipeView)findViewById(R.id.b_left);
        tmp.middleRunner = new Runner() {
            @Override
            public void execute() {
                int pos = input.getSelectionStart();
                if(pos == 0)
                    return;
                input.setSelection(pos - 1);
            }
        };
        tmp.topRunner = new Runner() {
            @Override
            public void execute() {
                input.setSelection(0);
            }
        };

        tmp = (SwipeView)findViewById(R.id.b_right);
        tmp.middleRunner = new Runner() {
            @Override
            public void execute() {
                Editable text = input.getText();
                int pos = input.getSelectionStart();
                if(pos == text.length())
                    return;
                input.setSelection(pos + 1);
            }
        };
        tmp.topRunner = new Runner() {
            @Override
            public void execute() {
                Editable text = input.getText();
                input.setSelection(text.length());
            }
        };

        tmp = (SwipeView)findViewById(R.id.b_equals);
        tmp.middleRunner = new Runner() {
            @Override
            public void execute() {
                String expression = input.getText().toString().replaceAll(" ", "");
                try {
                    Operand<Double> answer =
                            ExpressionParserBuilder.buildForDouble().build(expression);
                    setResult(answer.evaluate(doubleValues).toString());
                } catch (Exception e) {
                    setResult(":-(");
                }

            }
        };
        tmp.topRunner = new Runner() {
            @Override
            public void execute() {
                try {
                String expression = input.getText().toString().replaceAll(" ", "");
                    Operand<BigInteger> answer =
                            ExpressionParserBuilder.buildForBigInteger().build(expression);
                    setResult(answer.evaluate(bigIntValues).toString());
                } catch (Exception e) {
                    setResult(":-[");
                }
            }
        };
    }

    private void setResult (String s) {
        int width = result.getWidth() - 10;
        int height = result.getHeight();
        int textSize = 120;
        boolean trimmed = true;
        while (trimmed) {
            textSize--;
            int linesCount = height / textSize;
            int maxCharacters = (width / textSize) * linesCount;
            trimmed = s.length() > maxCharacters;
        }
        Log.e("result", height + " " + width + " " + textSize);
        result.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
        result.setText(s);
    }

    private void setInputFieldSettings() {
        input = (EditText)findViewById(R.id.input);
        input.requestFocus();
        input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager) getSystemService(
                        Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(input.getWindowToken(), 0);
            }
        });
        input.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                InputMethodManager imm = (InputMethodManager)getSystemService(
                        Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(input.getWindowToken(), 0);
                return true;
            }
        });
    }

}

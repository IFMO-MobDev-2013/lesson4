package ru.ifmo.mobdev.loboda.calc;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.*;

public class MyActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main);

        ((TextView) findViewById(R.id.textView)).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                return;
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                return;
            }

            @Override
            public void afterTextChanged(Editable editable) {
                ((ScrollView) findViewById(R.id.scrollView)).fullScroll(View.FOCUS_DOWN);
                char lastChar = editable.length() > 0 ? editable.charAt(editable.length() - 1) : ' ';
                boolean clickable = false;
                boolean clickable2 = false;
                if (lastChar == ')' || (lastChar <= '9' && lastChar >= '0') || lastChar == '.') {
                    clickable = true;
                    clickable2 = true;
                }
                if (lastChar == ' ' || lastChar == '(') {
                    clickable2 = true;
                }
                ((Button) findViewById(R.id.button3)).setEnabled(clickable);
                ((Button) findViewById(R.id.button7)).setEnabled(clickable);
                ((Button) findViewById(R.id.button11)).setEnabled(clickable2);
                ((Button) findViewById(R.id.button15)).setEnabled(clickable2);
            }
        });

        OnClickListener onClickListener = new OnClickListener() {
            int opened = 0;
            boolean isResult = false;

            @Override
            public void onClick(View view) {
                Button button = (Button) view;
                Button point = (Button) findViewById(R.id.button17);
                String text = button.getText().toString();
                TextView textView = (TextView) findViewById(R.id.textView);
                if (isResult && !text.equals("=")) {
                    textView.setText("");
                    isResult = false;
                }
                CharSequence sequence = textView.getText();
                char lastChar = sequence.length() > 0 ? sequence.charAt(sequence.length() - 1) : ' ';
                if(text.equals("=")){
                    if((lastChar == '+' || lastChar == '-' || lastChar == '*' || lastChar == '/') || (opened > 0)){
                        Toast.makeText(view.getContext(), "Incorrect expression", 1).show();
                        return;
                    }
                }
                if (text.equals("<=")) {
                    if (sequence.length() > 0 && lastChar == '(') {
                        opened -= 1;
                    }
                    if (sequence.length() > 0 && lastChar == ')') {
                        opened += 1;
                    }
                    if(lastChar == '.'){
                        point.setEnabled(true);
                    }
                    textView.setText(sequence.subSequence(0, sequence.length() > 0 ? sequence.length() - 1 : 0));
                    return;
                }
                if (text.equals("C")) {
                    textView.setText("");
                    opened = 0;
                    point.setEnabled(true);
                    return;
                }
                char sign = text.charAt(text.length() - 1);
                if (sign >= '0' && sign <= '9') {
                    if (lastChar == ')') {
                        textView.append("*");
                    }
                    textView.append(text);
                    return;
                }
                if (text.equals(".")) {
                    point.setEnabled(false);
                    textView.append(text);
                    return;
                }
                point.setEnabled(true);
                if (lastChar == '.') {
                    textView.append("0");
                }
                if (text.equals("=")) {
                    // Append missing brackets and remove sign from end
                    isResult = true;
                    textView.setText(Parser.parse(textView.getText().toString()).toString());
                    ((Button) findViewById(R.id.button3)).setEnabled(false);
                    ((Button) findViewById(R.id.button7)).setEnabled(false);
                    ((Button) findViewById(R.id.button11)).setEnabled(true);
                    ((Button) findViewById(R.id.button15)).setEnabled(true);
                    return;
                }
                if (text.equals("()")) {
                    opened += 1;
                    if ((lastChar >= '0' && lastChar <= '9') || lastChar == '.') {
                        if (opened == 1) {
                            textView.append("*(");
                        } else {
                            textView.append(")");
                            opened -= 2;
                        }
                        return;
                    }
                    if (lastChar == ')') {
                        if (opened == 1) {
                            textView.append("*(");
                        } else {
                            textView.append(")");
                            opened -= 2;
                        }
                        return;
                    }
                    textView.append("(");
                    return;
                }
                textView.append(text);
            }
        };
        // Set same onClickListener on all buttons
        ((Button) findViewById(R.id.button)).setOnClickListener(onClickListener);
        ((Button) findViewById(R.id.button1)).setOnClickListener(onClickListener);
        ((Button) findViewById(R.id.button2)).setOnClickListener(onClickListener);
        ((Button) findViewById(R.id.button3)).setOnClickListener(onClickListener);
        ((Button) findViewById(R.id.button4)).setOnClickListener(onClickListener);
        ((Button) findViewById(R.id.button5)).setOnClickListener(onClickListener);
        ((Button) findViewById(R.id.button6)).setOnClickListener(onClickListener);
        ((Button) findViewById(R.id.button7)).setOnClickListener(onClickListener);
        ((Button) findViewById(R.id.button8)).setOnClickListener(onClickListener);
        ((Button) findViewById(R.id.button9)).setOnClickListener(onClickListener);
        ((Button) findViewById(R.id.button10)).setOnClickListener(onClickListener);
        ((Button) findViewById(R.id.button11)).setOnClickListener(onClickListener);
        ((Button) findViewById(R.id.button12)).setOnClickListener(onClickListener);
        ((Button) findViewById(R.id.button13)).setOnClickListener(onClickListener);
        ((Button) findViewById(R.id.button14)).setOnClickListener(onClickListener);
        ((Button) findViewById(R.id.button15)).setOnClickListener(onClickListener);
        ((Button) findViewById(R.id.button16)).setOnClickListener(onClickListener);
        ((Button) findViewById(R.id.button17)).setOnClickListener(onClickListener);
        ((Button) findViewById(R.id.button18)).setOnClickListener(onClickListener);
    }
}

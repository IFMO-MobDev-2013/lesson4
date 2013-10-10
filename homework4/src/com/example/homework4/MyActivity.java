package com.example.homework4;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MyActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    private int depth = 0;
    String s = null;

    private boolean isDigitOrPar(char ch) {
        if ('0' <= ch && ch <= '9')
            return true;
        if (ch == ')')
            return true;

        return false;
    }

    private boolean isOperation(char ch) {
        if (ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch == '%')
            return true;
        else
            return false;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.linmain);

        depth = 0;

        //controls
        final TextView tv = (TextView) findViewById(R.id.textView);
        Button clear = (Button) findViewById(R.id.clear);
        Button back = (Button) findViewById(R.id.back);
        Button div = (Button) findViewById(R.id.div);
        Button mul = (Button) findViewById(R.id.mul);
        Button sub = (Button) findViewById(R.id.sub);
        Button add = (Button) findViewById(R.id.add);
        Button par = (Button) findViewById(R.id.par);
        Button is = (Button) findViewById(R.id.is);
        Button mod = (Button) findViewById(R.id.mod);

        //keyboard
        Button dot = (Button) findViewById(R.id.dot);
        Button n0 = (Button) findViewById(R.id.n0);
        Button n1 = (Button) findViewById(R.id.n1);
        Button n2 = (Button) findViewById(R.id.n2);
        Button n3 = (Button) findViewById(R.id.n3);
        Button n4 = (Button) findViewById(R.id.n4);
        Button n5 = (Button) findViewById(R.id.n5);
        Button n6 = (Button) findViewById(R.id.n6);
        Button n7 = (Button) findViewById(R.id.n7);
        Button n8 = (Button) findViewById(R.id.n8);
        Button n9 = (Button) findViewById(R.id.n9);

        par.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s = tv.getText().toString();
                if (depth == 0) {
                    if (s.length() == 0 || !isDigitOrPar(s.charAt(s.length() - 1))) {
                        s = s.concat("(");
                    } else {
                        s = s.concat("*(");
                    }

                    ++depth;
                    tv.setText(s);
                    return;
                }

                if (depth > 0) {
                    if (isDigitOrPar(s.charAt(s.length() - 1))) {
                        s = s.concat(")");
                        --depth;
                    } else {
                        s = s.concat("(");
                        ++depth;
                    }
                    tv.setText(s);
                    return;
                }
                // case depth < 0 is impossible
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s = tv.getText().toString();
                if (s.length() == 0)
                    return;
                if (s.charAt(s.length() - 1) == '(')
                    --depth;
                if (s.charAt(s.length() - 1) == ')')
                    ++depth;

                s = s.substring(0, s.length() - 1);
                tv.setText(s);
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv.setText("");
                depth = 0;
            }
        });

        div.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s = tv.getText().toString();
                if (s.length() == 0) {
                    Toast.makeText(MyActivity.this, "check your input", 1000).show();
                    return;
                }
                if (isOperation(s.charAt(s.length() - 1))) {
                    s = s.substring(0, s.length() - 1);
                }
//                if(s.charAt(s.length()-1) == '/')
//                {
//                    return;
//                }
                s = s.concat("/");
                tv.setText(s);
            }
        });

        mul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s = tv.getText().toString();
                if (s.length() == 0) {
                    Toast.makeText(MyActivity.this, "check your input", 1000).show();
                    return;
                }
                if (isOperation(s.charAt(s.length() - 1))) {
                    s = s.substring(0, s.length() - 1);
                }
//                if(s.charAt(s.length()-1) == '*')
//                {
//                    return;
//                }
                s = s.concat("*");
                tv.setText(s);
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s = tv.getText().toString();

                if (s.length() > 0 && s.charAt(s.length() - 1) == '+') {
                    return;
                }
                if (s.length() > 0 && isOperation(s.charAt(s.length() - 1))) {
                    s = s.substring(0, s.length() - 1);
                }
                s = s.concat("+");
                tv.setText(s);
            }
        });

        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s = tv.getText().toString();

                if (s.length() > 0 && s.charAt(s.length() - 1) == '-') {
                    s = s.substring(0, s.length() - 1);
                    s = s.concat("+");
                    tv.setText(s);
                    return;
                }
                if (s.length() > 0 && isOperation(s.charAt(s.length() - 1))) {
                    s = s.substring(0, s.length() - 1);
                }
                s = s.concat("-");
                tv.setText(s);
            }
        });

        mod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s = tv.getText().toString();
                if (s.length() == 0) {
                    Toast.makeText(MyActivity.this, "check your input", 1000).show();
                    return;
                }
                if (s.length() > 0 && isOperation(s.charAt(s.length() - 1))) {
                    s = s.substring(0, s.length() - 1);
                }
//                if(s.charAt(s.length()-1) == '%')
//                {
//                    return;
//                }
                s = s.concat("%");
                tv.setText(s);
            }
        });

        is.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(depth >0)
                {
                    s = "";
                    while(depth>0)
                    {
                        s = s.concat(")");
                        --depth;
                    }
                    tv.append(s);
                }
                s = tv.getText().toString(); ///////////////////////////////
                // s = "-7+2";
                DecimalFormat name = new DecimalFormat("#.#####");
                try {
                    double res = RecParser.parse(s).evaluate();
                    if (res != res) {
                        Toast.makeText(MyActivity.this, "NaN!", 1000).show();
                        tv.setText("");
                        return;
                    }

                    if (new Double(res).isInfinite()) {
                        Toast.makeText(MyActivity.this, "INF!", 1000).show();
                        tv.setText("");
                        return;
                    }

                    s =  name.format(res);
                    s = s.replaceAll(",", ".");
                    tv.setText(s);

                } catch (ParsException pexc) {
                    tv.setText("");
                    Toast.makeText(MyActivity.this, pexc.getMessage(), 1000).show();
                } catch (Exception exc) {
                    tv.setText("");
                    Toast.makeText(MyActivity.this, "FATAL ERROR" + exc.getMessage(), 3000).show();
                } finally {
                    depth = 0;
                }
            }
        });

        n0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s = tv.getText().toString();
                s = s.concat("0");
                tv.setText(s);
            }
        });

        n1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s = tv.getText().toString();
                s = s.concat("1");
                tv.setText(s);
            }
        });

        n2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s = tv.getText().toString();
                s = s.concat("2");
                tv.setText(s);
            }
        });

        n3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s = tv.getText().toString();
                s = s.concat("3");
                tv.setText(s);
            }
        });

        n4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s = tv.getText().toString();
                s = s.concat("4");
                tv.setText(s);
            }
        });

        n5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s = tv.getText().toString();
                s = s.concat("5");
                tv.setText(s);
            }
        });

        n6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s = tv.getText().toString();
                s = s.concat("6");
                tv.setText(s);
            }
        });

        n7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s = tv.getText().toString();
                s = s.concat("7");
                tv.setText(s);
            }
        });

        n8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s = tv.getText().toString();
                s = s.concat("8");
                tv.setText(s);
            }
        });

        n9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s = tv.getText().toString();
                s = s.concat("9");
                tv.setText(s);
            }
        });

        dot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s = tv.getText().toString();
                if (s.length() == 0) {
                    s = "0.";
                    return;
                }
                if (s.charAt(s.length() - 1) == '.') {
                    return;
                }
                s = s.concat(".");
                tv.setText(s);
            }
        });
    }
}

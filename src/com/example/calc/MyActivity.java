package com.example.calc;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import junit.framework.TestCase;
import junit.framework.TestCase;
import junit.framework.TestResult;

public class MyActivity extends Activity {
    public class TestModule extends TestCase {
        private int testCount = 0;
        @Override
        protected void setUp() throws Exception {
            super.setUp();

        }
        private void checkTest(String countString, double result) {
            testCount++;
            String calc = "";
            try {
                calc = Double.toString(rang_1(countString));

            } catch (Exception ex) {
                assertTrue(false);

            }

            double k = Math.abs(Double.parseDouble("" + calc) - result);
            assertTrue("Test #" + testCount + " failed", k < 1e-5);

        }
        public void runTest() {
            checkTest("1*1*1.1", 1 * 1 * 1.1);
            checkTest("-1/(-1)", -1 / (-1));
            checkTest("1*2*3*4*5", 1 * 2 * 3 * 4 * 5);
            checkTest("1*2*3*4*5/10", 1 * 2 * 3 * 4 * 5 / 10);
            checkTest("(2+2)*2", (2 + 2) * 2);
            checkTest("2+2*2", 2 + 2 * 2);
            checkTest("1/2/3+3", 1.0 / 2.0 / 3.0 + 3);
            checkTest("1/2/(3+3)", 1.0 / 2.0 / (3.0 + 3.0));
        }
    }


    static boolean f=false;
    public static boolean the_number(String s) {
        int k = 0;
        if (s.charAt(0)=='-' || s.charAt(0)=='+')
            s = s.substring(1, s.length());
        for (int i = 0; i < s.length(); i++)
            if (s.charAt(i) == '.') k++;
            else if (!(s.charAt(i) >= '0' && s.charAt(i) <= '9')) return false;
        if (k>1)return false;
        return true;
    }

    public static double rang_3(String s) {
        int bal = 0;
        for (int i = s.length()-1; i >=0; i--) {
            if (s.charAt(i) == '(') bal++;
            if (s.charAt(i) == ')') bal--;
            if (s.charAt(i) == '*' && bal == 0) {
                return rang_1(s.substring(0, i)) * rang_1(s.substring(i + 1, s.length()));
            } else if (s.charAt(i) == '/' && bal == 0) {
                return rang_1(s.substring(0, i)) / rang_1(s.substring(i + 1, s.length()));
            }
        }
        if (s.charAt(0) == '(' && s.charAt(s.length() - 1) == ')')
        {
            s = s.substring(1, s.length() - 1);
            return rang_1(s);
        }
        f=true;
        return 0;
    }

    public static double rang_2(String s) {
        if (f==true)return 0;

        int bal = 0;
        for (int i = s.length()-1; i >=0; i--) {
            if (s.charAt(i) == '(') bal++;
            if (s.charAt(i) == ')') bal--;
            if (s.charAt(i) == '+' && bal == 0) {
                return rang_1(s.substring(0, i)) + rang_1(s.substring(i + 1, s.length()));
            } else if (s.charAt(i) == '-' && bal == 0) {
                return rang_1(s.substring(0, i)) - rang_1(s.substring(i + 1, s.length()));
            }
        }
        return rang_3(s);
    }

    public static double rang_1(String s) {
        if (f==true)return 0;
        if (s.length() == 0) return 0;
        if (the_number(s) == true) return Double.parseDouble(s);
        return rang_2(s);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        final TextView expression = (TextView) findViewById(R.id.text);
        final Button equally = (Button) findViewById(R.id.button_equally);
        final TextView answer = (TextView) findViewById(R.id.textView);

        final Button zero = (Button) findViewById(R.id.button_0);
        final Button one = (Button) findViewById(R.id.button_1);
        final Button two = (Button) findViewById(R.id.button_2);
        final Button three = (Button) findViewById(R.id.button_3);
        final Button four = (Button) findViewById(R.id.button_4);
        final Button five = (Button) findViewById(R.id.button_5);
        final Button six = (Button) findViewById(R.id.button_6);
        final Button seven = (Button) findViewById(R.id.button_7);
        final Button eight = (Button) findViewById(R.id.button_8);
        final Button nine = (Button) findViewById(R.id.button_9);
        final Button backspace = (Button) findViewById(R.id.button_backspace);
        final Button C = (Button) findViewById(R.id.button_C);
        final Button division = (Button) findViewById(R.id.button_division);
        final Button times = (Button) findViewById(R.id.button_times);
        final Button minus = (Button) findViewById(R.id.button_minus);
        final Button plus = (Button) findViewById(R.id.button_plus);
        final Button point = (Button) findViewById(R.id.button_point);
        final Button open_bracket = (Button) findViewById(R.id.button_open);
        final Button close_bracket = (Button) findViewById(R.id.button_close);


        /*TestCase test = new TestModule();
        TestResult result = test.run();

        if (!result.wasSuccessful())
        {
            answer.setText("ТЕСТЫ НЕ ПРОЙДЕНЫ");
        }else
        {
            answer.setText("ТЕСТЫ ПРОЦДЕНЫ");
        }*/
        equally.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = expression.getText().toString();
                f=false;
                rang_1(text);
                if (f==false)
               {
                if (text.length() == 0) answer.setText("");
                answer.setText(Double.toString(rang_1(text)));
                }else
                    answer.setText("ERROR");
            }
        }
        );

        zero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                expression.setText(expression.getText() + "0");
            }
        });

        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                expression.setText(expression.getText() + "1");
            }
        });


        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                expression.setText(expression.getText() + "2");
            }
        });

        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                expression.setText(expression.getText() + "3");
            }
        });

        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                expression.setText(expression.getText() + "4");
            }
        });

        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                expression.setText(expression.getText() + "5");
            }
        });

        six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                expression.setText(expression.getText() + "6");
            }
        });

        seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                expression.setText(expression.getText() + "7");
            }
        });
        eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                expression.setText(expression.getText() + "8");
            }
        });

        nine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                expression.setText(expression.getText() + "9");
            }
        });

        backspace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (expression.getText().toString().length() == 0) expression.setText("");else
                expression.setText(expression.getText().toString().substring(0, expression.getText().toString().length() - 1));
            }
        });

        C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                expression.setText("");
            }
        });

        division.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                expression.setText(expression.getText() + "/");
            }
        });

        times.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                expression.setText(expression.getText() + "*");
            }
        });

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                expression.setText(expression.getText() + "-");
            }
        });

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                expression.setText(expression.getText() + "+");
            }
        });

        point.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                expression.setText(expression.getText() + ".");
            }
        });

        open_bracket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                expression.setText(expression.getText() + "(");
            }
        });

        close_bracket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                expression.setText(expression.getText() + ")");
            }
        });
    }
}

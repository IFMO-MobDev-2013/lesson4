package com.example.Calculator;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MyActivity extends Activity {
    TextView textView;
    String expression;
    String lastSumbol;
    int brackets;
    boolean point;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        textView = (TextView) findViewById(R.id.textView);
        expression = new String();
        lastSumbol = new String("");
        brackets = 0;
        point = false;
    }

    public void onClick_equally(View view) throws CalculationException {
        try {
            if (brackets != 0 || lastSumbol.equals("leftBr") || lastSumbol.equals("token")) {
                error("Введено неверное выражение");
            } else {
                expression = textView.getText().toString();
                if (expression.equals("")) {
                    error("Введено неверное выражение");
                } else {
                    Parser parser = new Parser();
                    Double answer = parser.operationParser(expression);
                    if (answer - answer.intValue() == 0) {
                        Integer ans = answer.intValue();
                        point = false;
                        expression = ans.toString();
                    } else {
                        answer = new BigDecimal(answer).setScale(10, RoundingMode.HALF_UP).doubleValue();
                        expression = answer.toString();
                        point = true;
                    }
                    textView.setText(expression);
                    brackets = 0;
                    lastSumbol = "digit";
                }
            }
        } catch (CalculationException e) {
            error(e.getMessage());
        }
    }

    public void onClick_C(View view) {
        expression = textView.getText().toString();
        if (!expression.equals("")) {
            String str = expression.substring(expression.length() - 1, expression.length());
            if (str.equals(".")) {
                point = false;
            }
            if (str.equals("(")) {
                brackets--;
            }
            if (str.equals(")")) {
                brackets++;
            }
            expression = expression.substring(0, expression.length() - 1);
            if (expression.equals("")) {
                lastSumbol = "";
            } else {
                str = expression.substring(expression.length() - 1, expression.length());
                if (str.equals(".")) {
                    lastSumbol = "point";
                } else {
                    if (str.equals("(")) {
                        lastSumbol = "leftBr";
                    } else {
                        if (str.equals(")")) {
                            lastSumbol = "rightBr";
                        } else {
                            if (str.equals("+") || str.equals("/") || str.equals("*") || str.equals("-")) {
                                lastSumbol = "token";
                            } else {
                                lastSumbol = "digit";
                            }
                        }
                    }
                }
            }
        }
        textView.setText(expression);
    }

    public void onClick_CE(View view) {
        expression = "";
        textView.setText(expression);
        lastSumbol = "";
        brackets = 0;
        point = false;
    }

    public void onClick_1(View view) {
        if (lastSumbol.equals("rightBr")) {
            error("Введено неверное выражение");
        } else {
            expression = expression.concat("1");
            textView.setText(expression);
            lastSumbol = "digit";
        }
    }

    public void onClick_2(View view) {
        if (lastSumbol.equals("rightBr")) {
            error("Введено неверное выражение");
        } else {
            expression = expression.concat("2");
            textView.setText(expression);
            lastSumbol = "digit";
        }
    }

    public void onClick_3(View view) {
        if (lastSumbol.equals("rightBr")) {
            error("Введено неверное выражение");
        } else {
            expression = expression.concat("3");
            textView.setText(expression);
            lastSumbol = "digit";
        }
    }

    public void onClick_4(View view) {
        if (lastSumbol.equals("rightBr")) {
            error("Введено неверное выражение");
        } else {
            expression = expression.concat("4");
            textView.setText(expression);
            lastSumbol = "digit";
        }
    }

    public void onClick_5(View view) {
        if (lastSumbol.equals("rightBr")) {
            error("Введено неверное выражение");
        } else {
            expression = expression.concat("5");
            textView.setText(expression);
            lastSumbol = "digit";
        }
    }

    public void onClick_6(View view) {
        if (lastSumbol.equals("rightBr")) {
            error("Введено неверное выражение");
        } else {
            expression = expression.concat("6");
            textView.setText(expression);
            lastSumbol = "digit";
        }
    }

    public void onClick_7(View view) {
        if (lastSumbol.equals("rightBr")) {
            error("Введено неверное выражение");
        } else {
            expression = expression.concat("7");
            textView.setText(expression);
            lastSumbol = "digit";
        }
    }

    public void onClick_8(View view) {
        if (lastSumbol.equals("rightBr")) {
            error("Введено неверное выражение");
        } else {
            expression = expression.concat("8");
            textView.setText(expression);
            lastSumbol = "digit";
        }
    }

    public void onClick_9(View view) {
        if (lastSumbol.equals("rightBr")) {
            error("Введено неверное выражение");
        } else {
            expression = expression.concat("9");
            textView.setText(expression);
            lastSumbol = "digit";
        }
    }

    public void onClick_0(View view) {
        if (lastSumbol.equals("rightBr")) {
            error("Введено неверное выражение");
        } else {
            expression = expression.concat("0");
            textView.setText(expression);
            lastSumbol = "digit";
        }
    }

    public void onClick_point(View view) {
        if (lastSumbol.equals("rightBr") || lastSumbol.equals("point") || point) {
            error("Введено неверное выражение");
        } else {
            if (!lastSumbol.equals("digit")) {
                expression = expression.concat("0.");
            } else {
                expression = expression.concat(".");
            }
            textView.setText(expression);
            lastSumbol = "point";
            point = true;
        }
    }

    public void onClick_leftBr(View view) {
        if (lastSumbol.equals("digit") || lastSumbol.equals("rightBr") || lastSumbol.equals("point")) {
            error("Введено неверное выражение");
        } else {
            expression = expression.concat("(");
            textView.setText(expression);
            brackets++;
            lastSumbol = "leftBr";
            point = false;
        }
    }

    public void onClick_RightBr(View view) {
        if (brackets == 0 || lastSumbol.equals("token") || lastSumbol.equals("leftBr") || lastSumbol == "") {
            error("Введено неверное выражение");
        } else {
            expression = expression.concat(")");
            textView.setText(expression);
            brackets--;
            lastSumbol = "rightBr";
            point = false;
        }
    }

    public void onClick_Plus(View view) {
        if (lastSumbol.equals("token")) {
            error("Введено неверное выражение");
        } else {
            expression = expression.concat("+");
            textView.setText(expression);
            lastSumbol = "token";
            point = false;
        }
    }

    public void onClick_Minus(View view) {
        if (lastSumbol.equals("token")) {
            error("Введено неверное выражение");
        } else {
            expression = expression.concat("-");
            textView.setText(expression);
            lastSumbol = "token";
            point = false;
        }
    }

    public void onClick_Devision(View view) {
        if (lastSumbol.equals("token") || lastSumbol.equals("leftBr") || lastSumbol == "") {
            error("Введено неверное выражение");
        } else {
            expression = expression.concat("/");
            textView.setText(expression);
            lastSumbol = "token";
            point = false;
        }
    }

    public void onClick_Times(View view) {
        if (lastSumbol.equals("token")|| lastSumbol.equals("leftBr") || lastSumbol == "") {
            error("Введено неверное выражение");
        } else {
            expression = expression.concat("*");
            textView.setText(expression);
            lastSumbol = "token";
            point = false;
        }
    }

    public void error(String str) {
        Toast myToast = Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT);
        myToast.setGravity(Gravity.CENTER, 0, 0);
        myToast.show();
    }
}


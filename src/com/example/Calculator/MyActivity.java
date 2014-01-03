package com.example.Calculator;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.test.suitebuilder.TestMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import junit.framework.TestCase;
import junit.framework.TestResult;

public class MyActivity extends Activity {

    TextView tv;
    Button one, two, three, four, five, six, seven, eight, nine, zero;
    Button minus, plus, times, division, point, bracketsClose, bracketsOpen, clean, equal, backspace;
    String string;

    int balance;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        string = "";
        tv = (TextView) findViewById(R.id.tv);
        zero = (Button) findViewById(R.id.zero);
        one = (Button) findViewById(R.id.one);
        two = (Button) findViewById(R.id.two);
        three = (Button) findViewById(R.id.three);
        four = (Button) findViewById(R.id.four);
        five = (Button) findViewById(R.id.five);
        six = (Button) findViewById(R.id.six);
        seven = (Button) findViewById(R.id.seven);
        eight = (Button) findViewById(R.id.eight);
        nine = (Button) findViewById(R.id.nine);

        minus = (Button) findViewById(R.id.minus);
        plus = (Button) findViewById(R.id.plus);
        times = (Button) findViewById(R.id.times);
        division = (Button) findViewById(R.id.div);
        point = (Button) findViewById(R.id.point);
        bracketsClose = (Button) findViewById(R.id.bracketsclose);
        bracketsOpen = (Button) findViewById(R.id.bracketsopen);
        backspace = (Button) findViewById(R.id.backspace);
        clean = (Button) findViewById(R.id.clean);
        equal = (Button) findViewById(R.id.equal);

        balance = 0;
        tv.setText("");
        View.OnClickListener oclBtn = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.one:
                        if(isOk(string + '1')) {
                            string += '1';
                            tv.setText(string);
                        }
                        break;
                    case R.id.two:
                        if(isOk(string + '2')) {
                            string += '2';
                            tv.setText(string);
                        }
                        break;
                    case R.id.three:
                        if(isOk(string + '3')) {
                            string += '3';
                            tv.setText(string);
                        }
                        break;
                    case R.id.four:
                        if(isOk(string + '4')) {
                            string += '4';
                            tv.setText(string);
                        }
                        break;
                    case R.id.five:
                        if(isOk(string + '5')) {
                            string += '5';
                            tv.setText(string);
                        }
                        break;
                    case R.id.six:
                        if(isOk(string + '6')) {
                            string += '6';
                            tv.setText(string);
                        }
                        break;
                    case R.id.seven:
                        if(isOk(string + '7')) {
                            string += '7';
                            tv.setText(string);
                        }
                        break;
                    case R.id.eight:
                        if(isOk(string + '8')) {
                            string += '8';
                            tv.setText(string);
                        }
                        break;
                    case R.id.nine:
                        if(isOk(string + '9')) {
                            string += '9';
                            tv.setText(string);
                        }
                        break;
                    case R.id.zero:
                        if(isOk(string + '0')) {
                            string += '0';
                            tv.setText(string);
                        }
                        break;
                    case R.id.clean:
                        string = "";
                        balance = 0;
                        tv.setText(string);
                        break;
                    case R.id.div:
                        if(isOk(string + '/')) {
                            string += '/';
                            tv.setText(string);
                        }
                        break;
                    case R.id.times:
                        if(isOk(string + '*')) {
                            string += '*';
                            tv.setText(string);
                        }
                        break;
                    case R.id.backspace:
                        if(string.length() > 0) {
                           if(string.charAt(string.length()-1) == '(') {
                                balance--;
                            } else if(string.charAt(string.length()-1) == ')') {
                                balance++;
                            }

                            string = string.substring(0, string.length()-1);
                        }
                        tv.setText(string);
                        break;
                    case R.id.minus:
                        if(isOk(string + '-')) {
                            string += '-';
                            tv.setText(string);
                        }
                        break;
                    case R.id.plus:
                        if(isOk(string + '+')) {
                            string += '+';
                            tv.setText(string);
                        }
                        break;
                    case R.id.equal:
                        if(string.length() != 0) {
                            if(balanceIsGood()) {
                                if(isArithmeticOperation(string.charAt(string.length()-1))
                                        || isPoint(string.charAt(string.length()-1))) {
                                    Toast.makeText(getApplicationContext(), "ILLEGAL", Toast.LENGTH_SHORT).show();
                                }
                                char e = string.charAt(string.length() -1);
                                if(!isArithmeticOperation(e) && !isPoint(e) && balanceIsGood()) {
                                    GetEqual gE = new GetEqual(string);
                                    string = gE.s();
                                    tv.setText(string);
                                    break;
                                }
                            } else
                                Toast.makeText(getApplicationContext(), "ILLEGAL", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case R.id.bracketsopen:
                        if(isOk(string + '(')) {
                            balance++;
                            string += '(';
                            tv.setText(string);
                        }
                        break;
                    case R.id.bracketsclose:
                        if(isOk(string + ')')) {
                            balance--;
                            string += ')';
                            tv.setText(string);
                        }
                        break;
                    case R.id.point:
                        if(isBrackets(string.charAt(string.length()-1))) {

                        } else if(isOk(string + '.')) {
                            string += '.';
                            tv.setText(string);
                        }
                        break;
                }

            }
        };
        one.setOnClickListener(oclBtn);
        two.setOnClickListener(oclBtn);
        three.setOnClickListener(oclBtn);
        four.setOnClickListener(oclBtn);
        five.setOnClickListener(oclBtn);
        six.setOnClickListener(oclBtn);
        seven.setOnClickListener(oclBtn);
        eight.setOnClickListener(oclBtn);
        nine.setOnClickListener(oclBtn);
        zero.setOnClickListener(oclBtn);
        clean.setOnClickListener(oclBtn);
        division.setOnClickListener(oclBtn);
        times.setOnClickListener(oclBtn);
        backspace.setOnClickListener(oclBtn);
        minus.setOnClickListener(oclBtn);
        plus.setOnClickListener(oclBtn);
        equal.setOnClickListener(oclBtn);
        bracketsClose.setOnClickListener(oclBtn);
        bracketsOpen.setOnClickListener(oclBtn);
        point.setOnClickListener(oclBtn);
    }

    private boolean balanceIsGood() {
        if(balance == 0)
            return true;
        return false;
    }

    private boolean isOk(String s) {
        if(s.length() == 1) {
            char b = s.charAt(0);
            if(b == '-')
                return true;
            if(isNumber(b) || b == '(') {
                return true;
            }
            return false;
        } else {
            char b = s.charAt(s.length() - 2);
            char e = s.charAt(s.length() - 1);
            if(isBrackets(e)) {
                if(e == ')')
                    if(balance == 0)
                        return false;
                if(e == '(') {
                    if(b == ')' || isNumber(b) || isPoint(b))
                        return false;
                    return true;
                }
                if(b == ')') {
                    return true;
                } else if(b == '(')
                    return false;
            }
            if(b == '(') {
                if(e == '(') {
                    return true;
                }
                if(isArithmeticOperation(e)) {
                    if(e == '-' || e == '+')
                        return true;
                    else
                        return false;
                }
            }
            if(b == ')') {
                if(isPoint(e) || isNumber(e))
                    return false;
            }
            if(isNumber(b)) {
                if(e == '(') {
                    return false;
                }
                if(isPoint(e)) {
                    for(int i = s.length()-2; i >= 0; i--) {
                        if(s.charAt(i) == '.')
                            return false;
                        if(!isNumber(s.charAt(i)))
                            break;
                    }
                }
                return true;
            }
            if(isArithmeticOperation(b)) {
                if(isArithmeticOperation(e))
                    return false;
                if(isPoint(e))
                    return false;
                if(e == ')')
                    return false;
            }
            if(isPoint(b)) {
                if(!isNumber(e)) {
                    return false;
                }

                return true;
            }


        }

        return true;
    }

    private boolean isPoint(char b) {
        if(b == '.')
            return true;
        return false;
    }

    private boolean isArithmeticOperation(char b) {
        if(b == '+' || b == '-' || b == '*' || b == '/')
            return true;
        return false;
    }

    private boolean isBrackets(char b) {
        if(b == '(' || b == ')')
            return true;
        return false;
    }

    private boolean isNumber(char b) {
        if(b >= '0' && b <= '9')
            return true;
        return false;
    }

}

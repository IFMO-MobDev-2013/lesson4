package com.example.lesson4;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Руслан
 * Date: 09.10.13
 * Time: 0:22
 * To change this template use File | Settings | File Templates.
 */

public class MainActivity extends Activity {
    String input = "";
    boolean operation_lock = false;
    boolean num_lock = false;
    TextView text;
    int i, scope_balance = 0;
    Parser parser;
    List<Character> operations = Arrays.asList('+', '*', '-', '/', '%');

    private void operationButtonClicked(char operation) {
        if (!operation_lock) {
            input += operation;
            operation_lock = true;
            text.setText(input);
            num_lock = false;
        }

    }

    private void numericButtonClicked(int num) {
        if (!num_lock) {
            if (input.length() == 0 || input.charAt(input.length() - 1) != ')') {
                input += num;
            } else {
                input = input.substring(0, input.length() - 1) + num + ")";
            }
            operation_lock = false;
            text.setText(input);
        }
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Button[] numeric = new Button[10];
        numeric[0] = (Button) findViewById(R.id.num0Button);
        numeric[1] = (Button) findViewById(R.id.num1Button);
        numeric[2] = (Button) findViewById(R.id.num2Button);
        numeric[3] = (Button) findViewById(R.id.num3Button);
        numeric[4] = (Button) findViewById(R.id.num4Button);
        numeric[5] = (Button) findViewById(R.id.num5Button);
        numeric[6] = (Button) findViewById(R.id.num6Button);
        numeric[7] = (Button) findViewById(R.id.num7Button);
        numeric[8] = (Button) findViewById(R.id.num8Button);
        numeric[9] = (Button) findViewById(R.id.num9Button);

        Button plusButton = (Button) findViewById(R.id.plusButton);
        Button subtractButton = (Button) findViewById(R.id.subtractButton);
        Button timesButton = (Button) findViewById(R.id.timesButton);
        Button divisionButton = (Button) findViewById(R.id.divisionButton);
        Button moduleDivisionButton = (Button) findViewById(R.id.moduleDivisionButton);
        Button dotButton = (Button) findViewById(R.id.dotButton);
        Button leftScopeButton = (Button) findViewById(R.id.leftScopeButton);
        Button rightScopeButton = (Button) findViewById(R.id.rightScopeButton);
        Button resetButton = (Button) findViewById(R.id.resetButton);
        Button deleteButton = (Button) findViewById(R.id.deleteButton);
        Button unaryOpButton = (Button) findViewById(R.id.unaryOpButton);
        Button computeButton = (Button) findViewById(R.id.computeButton);
        text = (TextView) findViewById(R.id.textView);

        plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operationButtonClicked('+');
            }
        });

        subtractButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operationButtonClicked('-');
            }
        });

        timesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operationButtonClicked('*');
            }
        });

        divisionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operationButtonClicked('/');
            }
        });

        moduleDivisionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operationButtonClicked('%');
            }
        });

        for (i = 0; i < 10; i++) {
            numeric[i].setOnClickListener(new View.OnClickListener() {
                final int x = i;

                @Override
                public void onClick(View v) {
                    numericButtonClicked(x);
                }
            });
        }

        dotButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        leftScopeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (input.length() == 0 ||
                        operations.contains(input.charAt(input.length() - 1)) || input.charAt(input.length() - 1) == '(') {
                    scope_balance++;
                    input += "(";
                    text.setText(input);
                    operation_lock = true;
                    num_lock = false;
                }
            }
        });

        rightScopeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (scope_balance > 0 &&
                        (Character.isDigit(input.charAt(input.length() - 1)) || input.charAt(input.length() - 1) == ')')) {
                    scope_balance--;
                    input += ")";
                    num_lock = true;
                    operation_lock = false;
                    text.setText(input);
                }
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!input.isEmpty()) {
                    char c = input.charAt(input.length() - 1);
                    input = input.substring(0, input.length() - 1);
                    if (c == ')')
                        scope_balance++;
                    else if (c == '(')
                        scope_balance--;
                    else if (operations.contains(c)){
                        operation_lock = false;
                        if (input.length() == 0 || Character.isDigit(input.length() - 1))
                           num_lock = false;
                        else if (input.charAt(input.length() - 1) == ')'){
                            int j = input.length() - 1;
                            while(j >= 0 && Character.isDigit(input.charAt(j))) j--;
                            if (j >= 1 && input.charAt(j) == '-' && input.charAt(j - 1) == '(')
                                num_lock = false;
                        }
                    }
                    text.setText(input);
                }
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input = "";
                scope_balance = 0;
                num_lock = false;
                operation_lock = true;
                text.setText(input);
            }
        });

        unaryOpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (input.length() > 0) {
                    int i = input.length() - 1;
                    if (input.charAt(i) == ')') {
                        i--;
                        while (i >= 0 && (input.charAt(i) >= '0' && input.charAt(i) <= '9' || input.charAt(i) == '.')) {
                            i--;
                        }
                        if (i >= 1 && input.charAt(i) == '-' && input.charAt(i - 1) == '(') {
                            input = input.substring(0, i - 1) + input.substring(i + 1, input.length() - 1);
                        }
                    } else {
                        while (i >= 0 && (input.charAt(i) >= '0' && input.charAt(i) <= '9' || input.charAt(i) == '.')) {
                            i--;
                        }
                        if (i != input.length() - 1) {
                            i++;
                            input = input.substring(0, i) + "(-" + input.substring(i, input.length()) + ")";
                        }

                    }

                }
                text.setText(input);
            }
        });
        parser = new Parser();
        computeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    for (int i = 0; i < scope_balance; i++)
                        input += ")";
                    input = Double.toString(parser.parse(input));
                    text.setText(input);
                } catch (ArithmeticException e) {
                    text.setText("Error: " + e.getMessage());
                    input = "";
                } catch (Exception e) {
                    input = "";
                    text.setText("Wrong input");
                }

            }
        });

        dotButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (input.length() > 0 && Character.isDigit(input.charAt(input.length() - 1))) {
                    input += ".";
                    operation_lock = true;
                    text.setText(input);
                }
            }
        });

    }
}
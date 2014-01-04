package ru.ifmo.mobdev.calculator;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import ru.ifmo.mobdev.calculator.engine.matchparserrun.MathParserRun;

public class CalcActivity extends Activity {

    Boolean resetAfterEqual = false;
    Boolean checkDot = true;
    String stringToCalculate = "";
    int countBrackets = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calc_main);

        final TextView calculatingText = (TextView) findViewById(R.id.calculatinString);
        final Button zeroButton = (Button) findViewById(R.id.zeroButton);
        final Button oneButton = (Button) findViewById(R.id.oneButton);
        final Button twoButton = (Button) findViewById(R.id.twoButton);
        final Button threeButton = (Button) findViewById(R.id.threeButton);
        final Button fourButton = (Button) findViewById(R.id.fourButton);
        final Button fiveButton = (Button) findViewById(R.id.fiveButton);
        final Button sixButton = (Button) findViewById(R.id.sixButton);
        final Button sevenButton = (Button) findViewById(R.id.sevenButton);
        final Button eightButton = (Button) findViewById(R.id.eightButton);
        final Button nineButton = (Button) findViewById(R.id.nineButton);

        final Button dotButton = (Button) findViewById(R.id.dotButton);
        final Button equalButton = (Button) findViewById(R.id.equalButton);
        final Button subButton = (Button) findViewById(R.id.subButton);
        final Button addButton = (Button) findViewById(R.id.addButton);
        final Button sumButton = (Button) findViewById(R.id.plusButton);
        final Button divButton = (Button) findViewById(R.id.divButton);
        final Button rCharButton = (Button) findViewById(R.id.rCharButton);
        final Button lCharButton = (Button) findViewById(R.id.leftCharButton);
        final Button clearButton = (Button) findViewById(R.id.clearButton);

        zeroButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (resetAfterEqual) {
                    stringToCalculate = "";
                    calculatingText.setText(stringToCalculate);
                    resetAfterEqual = false;
                }
                stringToCalculate += 0;
                calculatingText.setText(stringToCalculate);
            }
        });

        oneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (resetAfterEqual) {
                    stringToCalculate = "";
                    calculatingText.setText(stringToCalculate);
                    resetAfterEqual = false;
                }
                stringToCalculate += 1;
                calculatingText.setText(stringToCalculate);
            }
        });

        twoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (resetAfterEqual) {
                    stringToCalculate = "";
                    calculatingText.setText(stringToCalculate);
                    resetAfterEqual = false;
                }
                stringToCalculate += 2;
                calculatingText.setText(stringToCalculate);
            }
        });

        threeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (resetAfterEqual) {
                    stringToCalculate = "";
                    calculatingText.setText(stringToCalculate);
                    resetAfterEqual = false;
                }
                stringToCalculate += 3;
                calculatingText.setText(stringToCalculate);
            }
        });

        fourButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (resetAfterEqual) {
                    stringToCalculate = "";
                    calculatingText.setText(stringToCalculate);
                    resetAfterEqual = false;
                }
                stringToCalculate += 4;
                calculatingText.setText(stringToCalculate);
            }
        });

        fiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (resetAfterEqual) {
                    stringToCalculate = "";
                    calculatingText.setText(stringToCalculate);
                    resetAfterEqual = false;
                }
                stringToCalculate += 5;
                calculatingText.setText(stringToCalculate);
            }
        });

        sixButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (resetAfterEqual) {
                    stringToCalculate = "";
                    calculatingText.setText(stringToCalculate);
                    resetAfterEqual = false;
                }
                stringToCalculate += 6;
                calculatingText.setText(stringToCalculate);
            }
        });

        sevenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (resetAfterEqual) {
                    stringToCalculate = "";
                    calculatingText.setText(stringToCalculate);
                    resetAfterEqual = false;
                }
                stringToCalculate += 7;
                calculatingText.setText(stringToCalculate);
            }
        });

        eightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (resetAfterEqual) {
                    stringToCalculate = "";
                    calculatingText.setText(stringToCalculate);
                    resetAfterEqual = false;
                }
                stringToCalculate += 8;
                calculatingText.setText(stringToCalculate);
            }
        });

        nineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (resetAfterEqual) {
                    stringToCalculate = "";
                    calculatingText.setText(stringToCalculate);
                    resetAfterEqual = false;
                }
                stringToCalculate += 9;
                calculatingText.setText(stringToCalculate);
            }
        });

        dotButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (resetAfterEqual) {
                    stringToCalculate = "";
                    calculatingText.setText(stringToCalculate);
                    resetAfterEqual = false;
                }
                if (checkDot) {
                    if (!stringToCalculate.isEmpty()) {
                        if ((stringToCalculate.charAt(stringToCalculate.length() - 1) == '+' ||
                                stringToCalculate.charAt(stringToCalculate.length() - 1) == '\u2212' ||
                                stringToCalculate.charAt(stringToCalculate.length() - 1) == '\u00D7' ||
                                stringToCalculate.charAt(stringToCalculate.length() - 1) == '\u00F7' ||
                                stringToCalculate.charAt(stringToCalculate.length() - 1) == '(')) {
                            stringToCalculate += "0.";
                            calculatingText.setText(stringToCalculate);
                        } else {
                            stringToCalculate += '.';
                            calculatingText.setText(stringToCalculate);
                        }
                    } else {
                        stringToCalculate += '.';
                        calculatingText.setText(stringToCalculate);
                    }
                }
                checkDot = false;
            }
        });

        equalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MathParserRun.evaluate(stringToCalculate) != null)
                    stringToCalculate = Double.toString(MathParserRun.evaluate(stringToCalculate));
                else
                    stringToCalculate = "Error";
                calculatingText.setText(stringToCalculate);
                resetAfterEqual = true;
                checkDot = true;
            }
        });

        divButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!stringToCalculate.isEmpty()) {
                    if (stringToCalculate.charAt(stringToCalculate.length() - 1) == '\u2212') {
                        if (stringToCalculate.length() > 1 && (stringToCalculate.charAt(stringToCalculate.length() - 2) == '+' ||
                                stringToCalculate.charAt(stringToCalculate.length() - 2) == '\u2212' ||
                                stringToCalculate.charAt(stringToCalculate.length() - 2) == '\u00D7' ||
                                stringToCalculate.charAt(stringToCalculate.length() - 2) == '\u00F7')) {
                            stringToCalculate = stringToCalculate.substring(0, stringToCalculate.length() - 2) + '\u00F7';
                        }
                    }
                    if (!(stringToCalculate.charAt(stringToCalculate.length() - 1) == '+' ||
                            stringToCalculate.charAt(stringToCalculate.length() - 1) == '\u2212' ||
                            stringToCalculate.charAt(stringToCalculate.length() - 1) == '\u00D7' ||
                            stringToCalculate.charAt(stringToCalculate.length() - 1) == '\u00F7' ||
                            stringToCalculate.charAt(stringToCalculate.length() - 1) == '(')) {
                        stringToCalculate += '\u00F7';
                    } else {
                        if (!(stringToCalculate.charAt(stringToCalculate.length() - 1) == '(')) {
                            stringToCalculate = stringToCalculate.substring(0, stringToCalculate.length() - 1) + '\u00F7';
                        }
                    }
                }
                calculatingText.setText(stringToCalculate);
                checkDot = true;
                resetAfterEqual = false;
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!stringToCalculate.isEmpty()) {
                    if (stringToCalculate.charAt(stringToCalculate.length() - 1) == '\u2212') {
                        if (stringToCalculate.length() > 1 && (stringToCalculate.charAt(stringToCalculate.length() - 2) == '+' ||
                                stringToCalculate.charAt(stringToCalculate.length() - 2) == '\u2212' ||
                                stringToCalculate.charAt(stringToCalculate.length() - 2) == '\u00D7' ||
                                stringToCalculate.charAt(stringToCalculate.length() - 2) == '\u00F7')) {
                            stringToCalculate = stringToCalculate.substring(0, stringToCalculate.length() - 2) + '\u00D7';
                        }
                    }
                    if (!(stringToCalculate.charAt(stringToCalculate.length() - 1) == '+' ||
                            stringToCalculate.charAt(stringToCalculate.length() - 1) == '\u2212' ||
                            stringToCalculate.charAt(stringToCalculate.length() - 1) == '\u00D7' ||
                            stringToCalculate.charAt(stringToCalculate.length() - 1) == '\u00F7' ||
                            stringToCalculate.charAt(stringToCalculate.length() - 1) == '(')) {
                        stringToCalculate += '\u00D7';
                    } else {
                        if (!(stringToCalculate.charAt(stringToCalculate.length() - 1) == '(')) {
                            stringToCalculate = stringToCalculate.substring(0, stringToCalculate.length() - 1) + '\u00D7';
                        }
                    }
                }
                calculatingText.setText(stringToCalculate);
                checkDot = true;
                resetAfterEqual = false;
            }
        });

        subButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!(stringToCalculate.isEmpty() || stringToCalculate.charAt(stringToCalculate.length() - 1) == '(')) {
                    if (!(stringToCalculate.charAt(stringToCalculate.length() - 1) == '\u2212')) {
                        stringToCalculate += "\u2212";
                    } else {
                        stringToCalculate = stringToCalculate.substring(0, stringToCalculate.length() - 1) + "\u2212";
                    }

                } else {
                    stringToCalculate += "0\u2212";
                }
                calculatingText.setText(stringToCalculate);
                checkDot = true;
                resetAfterEqual = false;
            }
        });

        sumButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!stringToCalculate.isEmpty()) {
                    if (stringToCalculate.charAt(stringToCalculate.length() - 1) == '\u2212') {
                        if (stringToCalculate.length() > 1 && (stringToCalculate.charAt(stringToCalculate.length() - 2) == '+' ||
                                stringToCalculate.charAt(stringToCalculate.length() - 2) == '\u2212' ||
                                stringToCalculate.charAt(stringToCalculate.length() - 2) == '\u00D7' ||
                                stringToCalculate.charAt(stringToCalculate.length() - 2) == '\u00F7')) {
                            stringToCalculate = stringToCalculate.substring(0, stringToCalculate.length() - 2) + '+';
                        }
                    }
                    if (!(stringToCalculate.charAt(stringToCalculate.length() - 1) == '+' ||
                            stringToCalculate.charAt(stringToCalculate.length() - 1) == '\u2212' ||
                            stringToCalculate.charAt(stringToCalculate.length() - 1) == '\u00D7' ||
                            stringToCalculate.charAt(stringToCalculate.length() - 1) == '\u00F7' ||
                            stringToCalculate.charAt(stringToCalculate.length() - 1) == '(')) {
                        stringToCalculate += '+';
                    } else {
                        if (!(stringToCalculate.charAt(stringToCalculate.length() - 1) == '(')) {
                            stringToCalculate = stringToCalculate.substring(0, stringToCalculate.length() - 1) + '+';
                        }
                    }
                }
                calculatingText.setText(stringToCalculate);
                checkDot = true;
                resetAfterEqual = false;
            }
        });

        rCharButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (resetAfterEqual) {
                    stringToCalculate = "";
                    calculatingText.setText(stringToCalculate);
                    resetAfterEqual = false;
                }
                if (countBrackets != 0) {
                    stringToCalculate += ')';
                    calculatingText.setText(stringToCalculate);
                    checkDot = true;
                    countBrackets--;
                }
            }
        });

        lCharButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (resetAfterEqual) {
                    stringToCalculate = "";
                    calculatingText.setText(stringToCalculate);
                    resetAfterEqual = false;
                }

                if (!stringToCalculate.isEmpty()) {
                    if (stringToCalculate.charAt(stringToCalculate.length() - 1) == ')' ||
                            (stringToCalculate.charAt(stringToCalculate.length() - 1) - '0' >= 0 &&
                            stringToCalculate.charAt(stringToCalculate.length() - 1) - '0' <= 9 )) {
                        stringToCalculate += "×(";
                    } else {
                        stringToCalculate += "(";
                    }
                } else {
                    stringToCalculate += "(";
                }
                calculatingText.setText(stringToCalculate);
                checkDot = true;
                countBrackets++;
            }
        });

        clearButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (resetAfterEqual) {
                    stringToCalculate = "";
                    calculatingText.setText(stringToCalculate);
                    resetAfterEqual = false;
                }
                if (!stringToCalculate.isEmpty()) {
                    if (stringToCalculate.charAt(stringToCalculate.length() - 1) == '.') {
                        checkDot = true;
                    }

                    if (stringToCalculate.charAt(stringToCalculate.length() - 1) == ')') {
                        countBrackets++;
                    }

                    if (stringToCalculate.charAt(stringToCalculate.length() - 1) == '(') {
                        countBrackets--;
                    }
                    stringToCalculate = stringToCalculate.substring(0, stringToCalculate.length() - 1);

                }
                calculatingText.setText(stringToCalculate);
            }
        });

        clearButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                stringToCalculate = "";
                calculatingText.setText(stringToCalculate);
                return true;
            }
        });


    }

}

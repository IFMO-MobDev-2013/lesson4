package mazinva.Calculator;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MyActivity extends Activity {
    TextView textView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        textView = (TextView) findViewById(R.id.currentExpression);
        textView.setTextSize(30);

        Button calculateButton = (Button) findViewById(R.id.calculate);
        calculateButton.setOnClickListener(calculateListener);

        Button clearAllButton = (Button) findViewById(R.id.clearAll);
        clearAllButton.setOnClickListener(clearAllListener);

        Button deleteLastButton = (Button) findViewById(R.id.deleteLast);
        deleteLastButton.setOnClickListener(deleteLastListener);

        Button leftBracketButton = (Button) findViewById(R.id.leftBracket);
        leftBracketButton.setOnClickListener(leftBracketListener);

        Button rightBracketButton = (Button) findViewById(R.id.rightBracket);
        rightBracketButton.setOnClickListener(rightBracketListener);

        Button dotButton = (Button) findViewById(R.id.dot);
        dotButton.setOnClickListener(dotListener);

        Button plusButton = (Button) findViewById(R.id.plus);
        plusButton.setOnClickListener(plusListener);

        Button minusButton = (Button) findViewById(R.id.minus);
        minusButton.setOnClickListener(minusListener);

        Button timesButton = (Button) findViewById(R.id.times);
        timesButton.setOnClickListener(timesListener);

        Button divisionButton = (Button) findViewById(R.id.division);
        divisionButton.setOnClickListener(divisionListener);

        Button digitZeroButton = (Button) findViewById(R.id.digitZero);
        digitZeroButton.setOnClickListener(digitZeroListener);

        Button digitOneButton = (Button) findViewById(R.id.digitOne);
        digitOneButton.setOnClickListener(digitOneListener);

        Button digitTwoButton = (Button) findViewById(R.id.digitTwo);
        digitTwoButton.setOnClickListener(digitTwoListener);

        Button digitThreeButton = (Button) findViewById(R.id.digitThree);
        digitThreeButton.setOnClickListener(digitThreeListener);

        Button digitFourButton = (Button) findViewById(R.id.digitFour);
        digitFourButton.setOnClickListener(digitFourListener);

        Button digitFiveButton = (Button) findViewById(R.id.digitFive);
        digitFiveButton.setOnClickListener(digitFiveListener);

        Button digitSixButton = (Button) findViewById(R.id.digitSix);
        digitSixButton.setOnClickListener(digitSixListener);

        Button digitSevenButton = (Button) findViewById(R.id.digitSeven);
        digitSevenButton.setOnClickListener(digitSevenListener);

        Button digitEightButton = (Button) findViewById(R.id.digitEight);
        digitEightButton.setOnClickListener(digitEightListener);

        Button digitNineButton = (Button) findViewById(R.id.digitNine);
        digitNineButton.setOnClickListener(digitNineListener);

    }

    private View.OnClickListener calculateListener = new View.OnClickListener() {
        public void onClick(View v) {
            try {
                String s = textView.getText().toString();
                if (s.length() > 0) {
                    Double res = new Parser().parse(s).calculate();
                    if (res == Math.round(res)) {
                        textView.setText(((Integer) res.intValue()).toString());
                    } else {
                        textView.setText(res.toString());
                    }
                }
            } catch (SyntaxException e) {
                Toast toast = Toast.makeText(getApplicationContext(), e.get(), Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP, 0, 0);
                toast.show();
            }
        }
    };

    private View.OnClickListener clearAllListener = new View.OnClickListener() {
        public void onClick(View v) {
            textView.setText("");
        }
    };

    private View.OnClickListener deleteLastListener = new View.OnClickListener() {
        public void onClick(View v) {
            if (textView.getText().length() > 0) {
                String s = textView.getText().toString();
                int k = s.length();
                textView.setText(s.substring(0, k - 1));
            }
        }
    };

    private View.OnClickListener leftBracketListener = new View.OnClickListener() {
        public void onClick(View v) {
            textView.append("(");
        }
    };

    private View.OnClickListener rightBracketListener = new View.OnClickListener() {
        public void onClick(View v) {
            textView.append(")");
        }
    };

    private View.OnClickListener dotListener = new View.OnClickListener() {
        public void onClick(View v) {
            textView.append(".");
        }
    };

    private View.OnClickListener plusListener = new View.OnClickListener() {
        public void onClick(View v) {
            textView.append("+");
        }
    };

    private View.OnClickListener minusListener = new View.OnClickListener() {
        public void onClick(View v) {
            textView.append("-");
        }
    };

    private View.OnClickListener timesListener = new View.OnClickListener() {
        public void onClick(View v) {
            textView.append("*");
        }
    };

    private View.OnClickListener divisionListener = new View.OnClickListener() {
        public void onClick(View v) {
            textView.append("/");
        }
    };

    private View.OnClickListener digitZeroListener = new View.OnClickListener() {
        public void onClick(View v) {
            textView.append("0");
        }
    };

    private View.OnClickListener digitOneListener = new View.OnClickListener() {
        public void onClick(View v) {
            textView.append("1");
        }
    };

    private View.OnClickListener digitTwoListener = new View.OnClickListener() {
        public void onClick(View v) {
            textView.append("2");
        }
    };

    private View.OnClickListener digitThreeListener = new View.OnClickListener() {
        public void onClick(View v) {
            textView.append("3");
        }
    };

    private View.OnClickListener digitFourListener = new View.OnClickListener() {
        public void onClick(View v) {
            textView.append("4");
        }
    };

    private View.OnClickListener digitFiveListener = new View.OnClickListener() {
        public void onClick(View v) {
            textView.append("5");
        }
    };

    private View.OnClickListener digitSixListener = new View.OnClickListener() {
        public void onClick(View v) {
            textView.append("6");
        }
    };

    private View.OnClickListener digitSevenListener = new View.OnClickListener() {
        public void onClick(View v) {
            textView.append("7");
        }
    };

    private View.OnClickListener digitEightListener = new View.OnClickListener() {
        public void onClick(View v) {
            textView.append("8");
        }
    };

    private View.OnClickListener digitNineListener = new View.OnClickListener() {
        public void onClick(View v) {
            textView.append("9");
        }
    };

}

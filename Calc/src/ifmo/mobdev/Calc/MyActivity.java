package ifmo.mobdev.Calc;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class MyActivity extends Activity {
    Button zero, back, erase, equal, seven, eight, nine, mul, four, five, six, div, one, two, three, plus, point, opbr, clbr, minus;
    TextView screen;
    ImageView picture;
    String expr = "";
    boolean imgVis;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.main);
        zero = (Button) findViewById(R.id.zero);
        back = (Button) findViewById(R.id.back);
        erase = (Button) findViewById(R.id.erase);
        equal = (Button) findViewById(R.id.equal);
        seven = (Button) findViewById(R.id.seven);
        eight = (Button) findViewById(R.id.eight);
        nine = (Button) findViewById(R.id.nine);
        mul = (Button) findViewById(R.id.mul);
        four = (Button) findViewById(R.id.four);
        five = (Button) findViewById(R.id.five);
        six = (Button) findViewById(R.id.six);
        div = (Button) findViewById(R.id.div);
        one = (Button) findViewById(R.id.one);
        two = (Button) findViewById(R.id.two);
        three = (Button) findViewById(R.id.three);
        plus = (Button) findViewById(R.id.plus);
        point = (Button) findViewById(R.id.point);
        opbr = (Button) findViewById(R.id.opbr);
        clbr = (Button) findViewById(R.id.clbr);
        minus = (Button) findViewById(R.id.minus);

        picture = (ImageView) findViewById(R.id.imageView);
        picture.setAdjustViewBounds(true);
        picture.setVisibility(View.INVISIBLE);

        screen = (TextView) findViewById(R.id.expression);
        screen.setShadowLayer(5f, 3f, 3f, 0xFF101010);

        Display display =((WindowManager)getSystemService(WINDOW_SERVICE)).getDefaultDisplay();
        int height = display.getHeight();

        screen.setTextSize(height * 0.025f);

        Typeface digitalFont = Typeface.createFromAsset(this.getAssets(), "fonts/digital.ttf");
        screen.setTypeface(digitalFont);
        screen.setMovementMethod(new ScrollingMovementMethod());

        digitalFont = Typeface.createFromAsset(this.getAssets(), "fonts/pfonline.ttf");
        zero.setTypeface(digitalFont);
        one.setTypeface(digitalFont);
        two.setTypeface(digitalFont);
        three.setTypeface(digitalFont);
        four.setTypeface(digitalFont);
        five.setTypeface(digitalFont);
        six.setTypeface(digitalFont);
        seven.setTypeface(digitalFont);
        eight.setTypeface(digitalFont);
        nine.setTypeface(digitalFont);
        opbr.setTypeface(digitalFont);
        clbr.setTypeface(digitalFont);
        point.setTypeface(digitalFont);
        mul.setTypeface(digitalFont);
        div.setTypeface(digitalFont);
        plus.setTypeface(digitalFont);
        minus.setTypeface(digitalFont);
        erase.setTypeface(digitalFont);
        back.setTypeface(digitalFont);
        equal.setTypeface(digitalFont);

        zero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imgVis) {
                    picture.setVisibility(View.INVISIBLE);
                    imgVis = false;
                }
                expr += "0";
                screen.setText(expr);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imgVis) {
                    picture.setVisibility(View.INVISIBLE);
                    imgVis = false;
                }
                if (expr.length() > 0) {
                    if (expr.charAt(expr.length() - 1) == ' ') {
                        expr = expr.substring(0, expr.length() - 3);
                    } else {
                        expr = expr.substring(0, expr.length() - 1);
                    }
                    screen.setText(expr);
                }
            }
        });
        erase.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (imgVis) {
                    picture.setVisibility(View.INVISIBLE);
                    imgVis = false;
                }
                expr = "";
                screen.setText(expr);
            }
        });
        equal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double result = 0;
                boolean ch = false;
                try {
                    result = new Parser().parseExpression(expr).evaluate();
                    picture.setImageResource(R.drawable.ok);
                } catch (FunctionException e) {
                    ch = true;
                    switch (e.getMessage().charAt(0)) {
                        case 'd' :
                            picture.setImageResource(R.drawable.dbz);
                            break;
                        case 'o' :
                            picture.setImageResource(R.drawable.overflow);
                            break;
                        case 'e' :
                            picture.setImageResource(R.drawable.empty);
                            break;
                        case 'w' :
                            picture.setImageResource(R.drawable.wrongop);
                            break;
                        case 'n' :
                            picture.setImageResource(R.drawable.notenought);
                            break;
                        case 'i' :
                            picture.setImageResource(R.drawable.incorrect);
                            break;
                        default:
                            picture.setImageResource(R.drawable.strange);
                            break;
                    }
                    Toast toast = Toast.makeText(MyActivity.this, e.getMessage(), 3000);
                    toast.show();
                }

                picture.setVisibility(View.VISIBLE);
                imgVis = true;

                if (!ch) {
                    if ((int) result == result) {
                        expr = Integer.toString((int)result);
                    } else {
                        DecimalFormatSymbols dfs = new DecimalFormatSymbols(Locale.US);
                        DecimalFormat df = new DecimalFormat("#.####################", dfs);
                        expr = df.format(result);
                    }
                    screen.setText(expr);
                }
            }
        });
        seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imgVis) {
                    picture.setVisibility(View.INVISIBLE);
                    imgVis = false;
                }
                expr += "7";
                screen.setText(expr);
            }
        });
        eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imgVis) {
                    picture.setVisibility(View.INVISIBLE);
                    imgVis = false;
                }
                expr += "8";
                screen.setText(expr);
            }
        });
        nine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imgVis) {
                    picture.setVisibility(View.INVISIBLE);
                    imgVis = false;
                }
                expr += "9";
                screen.setText(expr);
            }
        });
        mul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imgVis) {
                    picture.setVisibility(View.INVISIBLE);
                    imgVis = false;
                }
                expr += " * ";
                screen.setText(expr);
            }
        });
        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imgVis) {
                    picture.setVisibility(View.INVISIBLE);
                    imgVis = false;
                }
                expr += "4";
                screen.setText(expr);
            }
        });
        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imgVis) {
                    picture.setVisibility(View.INVISIBLE);
                    imgVis = false;
                }
                expr += "5";
                screen.setText(expr);
            }
        });
        six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imgVis) {
                    picture.setVisibility(View.INVISIBLE);
                    imgVis = false;
                }
                expr += "6";
                screen.setText(expr);
            }
        });
        div.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imgVis) {
                    picture.setVisibility(View.INVISIBLE);
                    imgVis = false;
                }
                expr += " / ";
                screen.setText(expr);
            }
        });
        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imgVis) {
                    picture.setVisibility(View.INVISIBLE);
                    imgVis = false;
                }
                expr += "1";
                screen.setText(expr);
            }
        });
        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imgVis) {
                    picture.setVisibility(View.INVISIBLE);
                    imgVis = false;
                }
                expr += "2";
                screen.setText(expr);
            }
        });
        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imgVis) {
                    picture.setVisibility(View.INVISIBLE);
                    imgVis = false;
                }
                expr += "3";
                screen.setText(expr);
            }
        });
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imgVis) {
                    picture.setVisibility(View.INVISIBLE);
                    imgVis = false;
                }
                expr += " + ";
                screen.setText(expr);
            }
        });
        point.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imgVis) {
                    picture.setVisibility(View.INVISIBLE);
                    imgVis = false;
                }
                expr += ".";
                screen.setText(expr);
            }
        });
        opbr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imgVis) {
                    picture.setVisibility(View.INVISIBLE);
                    imgVis = false;
                }
                expr += " ( ";
                screen.setText(expr);
            }
        });
        clbr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imgVis) {
                    picture.setVisibility(View.INVISIBLE);
                    imgVis = false;
                }
                expr += " ) ";
                screen.setText(expr);
            }
        });
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imgVis) {
                    picture.setVisibility(View.INVISIBLE);
                    imgVis = false;
                }
                expr += " - ";
                screen.setText(expr);
            }
        });
    }
}

package com.example.calc;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import junit.framework.TestCase;
import junit.framework.TestResult;

import java.util.*;

public class MyActivity extends Activity {

    TextView editText;
    TextView textView;

    public class Tests extends TestCase {
        private int testCount = 0;
        @Override
        protected void setUp() throws Exception {
            super.setUp();
        }
        public void checkTest(String countString, double result) {
            testCount++;
            double calc = getDouble(unpack(execute(countString)));
            assertTrue("Test #" + testCount + " failed", Math.abs(calc - result) < 1e-5);
        }
        @Override
        public void runTest() {
            checkTest("1*2.0*1", 1 * 2.0 * 1);
            checkTest("-1/(-1)-1", -1 / (-1) - 1);
            checkTest("2+(-2-2)", 2+(-2-2));
            checkTest("2*(2+2)*2", 2*(2+2)*2.0);
            checkTest("(15)/10+1.5", (15) / 10.0 + 1.5);
            checkTest("-(-(-(+9)))", -(-(-(+9))));
            checkTest("2.0/3+3.0/3/3", 2.0/3+3.0/3/3);
        }
    }

    void addText(String c)
    {
        editText.setText(editText.getText().toString() + c);
    }

    boolean isDigit(char c)
    {
        return (c == '+' || c == '-' || c == '*' || c == '/');
    }
    boolean isAllDigit(char c)
    {
        return (c == '+' || c == '-' || c == '*' || c == '/' || c == '(' || c == ')');
    }
    void clearSymbol()
    {
        editText.setText(editText.getText().toString().substring(0, editText.getText().toString().length() - 1));
    }

    boolean isDouble(String s)
    {
        boolean q = true;
        for(int i = 1; i < s.length(); i++)
            if(isAllDigit(s.charAt(i)))
                return false;
        if(isAllDigit(s.charAt(0)) && (s.charAt(0) != '-'))
            return false;
        int k = 0;
        for(int i = 0; i < s.length(); i++)
            if(s.charAt(i) == '.')
                k++;
        if(k > 1)
            return false;
        try {
            Double.parseDouble(s);
        }
        catch (NumberFormatException ex)
        {
            q = false;
        }
        catch (NullPointerException ex)
        {
            q = false;
        }
        return q;
    }

    boolean isPackedDouble(String s)
    {
        if(s == null || s.length() < 3)
            return false;
        return (s.charAt(0) == '(' && s.charAt(s.length() - 1) == ')' && isDouble(s.substring(1, s.length() - 1)));
    }

    double getDouble(String s)
    {
        if(isDouble(s))
            return Double.parseDouble(s);
        if(isPackedDouble(s))
            return Double.parseDouble(s.substring(1, s.length() - 1));
        System.out.println("ERROR _" + s);
        return 0.0;
    }

    String execute(String e)
    {
        System.out.println("OP^   _" + e);
        if(e.isEmpty())
            return "Empty String";
        boolean q = false;
        for(int i = 0; i < e.length(); i++)
            if(e.charAt(i) >= '0' || e.charAt(i) <= '9')
                q = true;
        if(!q)
            return "String without numbers";
        if(isDouble(e))
            return "(" + e + ")";
        if(isPackedDouble(e))
            return e;
        System.out.println("go in");
        int brace[] = new int[e.length()];
        ArrayList<Integer> a = new ArrayList<Integer>();
        int k = 0;
        for(int i = 0; i < e.length(); i++)
        {
            if(e.charAt(i) == '(')
            {
                k++;
                a.add(i);
            }
            if(e.charAt(i) == ')')
            {
                if(a.size() == 0)
                    return "Wrong bracers";
                k--;
                brace[a.remove(a.size() - 1)] = i;
            }
            if(k < 0)
                return "Wrong bracers";
        }
        if(k != 0)
            return "Wrong bracers";
        String w;
        if(e.charAt(0) == '(' && brace[0] == e.length() - 1)
        {
            w = execute(e.substring(1, e.length() - 1));
            if(isPackedDouble(w))
                return w;
            if(isDouble(w))
                return "(" + getDouble(w) + ")";
            return w;
        }
        for(int i = 0; i < e.length() - 1; i++)
            if(isDigit(e.charAt(i)) && isDigit(e.charAt(i + 1)))
                return "Double digit";
        if(e.charAt(0) == '+')
            return execute(e.substring(1, e.length()));
        k = e.length();
        System.out.println("go middle");
        if(e.charAt(0) == '-')
        {
            if(e.charAt(1) == '(')
            {
                w = execute(e.substring(1, brace[1] + 1));
                if(isPackedDouble(w) || isDouble(w))
                    return execute("(" + (-1 * getDouble(w)) + ")" + e.substring(brace[1] + 1, e.length()));
                return w;
            }
            for(int i = 1; i < e.length(); i++)
                if((e.charAt(i) < '0' || e.charAt(i) > '9') && e.charAt(i) != '.')
                {
                    k = i;
                    break;
                }
            w = execute(e.substring(1, k));
            if(isPackedDouble(w) || isDouble(w))
                return execute("(" + (-1 * getDouble(w)) + ")" + e.substring(k, e.length()));
            return w;

        }

        for(int i = 0; i < e.length(); i++)
        {
            if(e.charAt(i) == '(')
                i = brace[i];
            else if(e.charAt(i) == '+' || e.charAt(i) == '-')
            {
                if(e.length() == i + 1)
                    return "Wrong digit";
                String s1 = execute(e.substring(0, i));
                if(!isDouble(s1) && !isPackedDouble(s1))
                    return s1;
                String s2;
                int kk = e.length();
                for(int j = i + 1; j < e.length(); j++)
                {
                    if(e.charAt(j) == '(')
                        j = brace[j];
                    else if(e.charAt(j) == '+' || e.charAt(j) == '-')
                    {
                        kk = j;
                        break;
                    }
                }
                s2 = execute(e.substring(i + 1, kk));
                if(!isDouble(s2) && !isPackedDouble(s2))
                    return s2;
                if(e.charAt(i) == '+')
                    return execute("(" + (getDouble(s1) + getDouble(s2)) + ")" + e.substring(kk, e.length()));
                else
                    return execute("(" + (getDouble(s1) - getDouble(s2)) + ")" + e.substring(kk, e.length()));
            }
        }
        for(int i = 1; i < e.length(); i++)
        {
            if(e.charAt(i) == '(')
                i = brace[i];
            else if(e.charAt(i) == '*' || e.charAt(i) == '/')
            {
                if(e.length() == i + 1)
                    return "Wrong digit";
                String s1 = execute(e.substring(0, i));
                if(!isDouble(s1) && !isPackedDouble(s1))
                    return s1;
                String s2;
                if(e.charAt(i + 1) == '(')
                {
                    s2 = execute(e.substring(i + 1, brace[i + 1] + 1));
                    if(!isDouble(s2) && !isPackedDouble(s2))
                        return s2;
                    if(e.charAt(i) == '/' && getDouble(s2) == 0.0)
                        return "Divide by zero";
                    if(e.charAt(i) == '*')
                        return execute("(" + (getDouble(s1) * getDouble(s2)) + ")" + e.substring(brace[i + 1] + 1, e.length()));
                    else
                        return execute("(" + (getDouble(s1) / getDouble(s2)) + ")" + e.substring(brace[i + 1] + 1, e.length()));
                }
                else
                {
                    int kk = e.length();
                    for(int j = i + 1; j < e.length(); j++)
                        if((e.charAt(j) < '0' || e.charAt(j) > '9') && e.charAt(j) != '.')
                        {
                            kk = j;
                            break;
                        }
                    s2 = execute(e.substring(i + 1, kk));
                    if(!isDouble(s2) && !isPackedDouble(s2))
                        return s2;
                    if(e.charAt(i) == '/' && getDouble(s2) == 0.0)
                        return "Divide by zero";
                    if(e.charAt(i) == '*')
                        return execute("(" + (getDouble(s1) * getDouble(s2)) + ")" + e.substring(kk, e.length()));
                    else
                        return execute("(" + (getDouble(s1) / getDouble(s2)) + ")" + e.substring(kk, e.length()));
                }
            }
        }
        return "Not a double";
    }

    String checkPoints(String c)
    {
        String s = new String(c);
        for(int i = 0; i < s.length(); i++)
            if(s.charAt(i) == ',')
                s = s.substring(0, i) + '.' + s.substring(i + 1);
        return s;
    }

    String unpack(String s)
    {
        if(isDouble(s))
            return s;
        if(isPackedDouble(s))
            return s.substring(1, s.length() - 1);
        return s;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        editText = (TextView) findViewById(R.id.editText);
        textView = (TextView) findViewById(R.id.textView);

        TestCase tests = new Tests();
        TestResult testResult = new TestResult();
        tests.run(testResult);

        if(!testResult.wasSuccessful())
            textView.setText("Tests was NOT successful");
        else
        {
            textView.setText("Tests was successful");
            Button[] button = new Button[20];
            button[0] =(Button) findViewById(R.id.button);
            button[1] =(Button) findViewById(R.id.button1);
            button[2] =(Button) findViewById(R.id.button2);
            button[3] =(Button) findViewById(R.id.button3);
            button[4] =(Button) findViewById(R.id.button4);
            button[5] =(Button) findViewById(R.id.button5);
            button[6] =(Button) findViewById(R.id.button6);
            button[7] =(Button) findViewById(R.id.button7);
            button[8] =(Button) findViewById(R.id.button8);
            button[9] =(Button) findViewById(R.id.button9);
            button[10] =(Button) findViewById(R.id.button10);
            button[11] =(Button) findViewById(R.id.button11);
            button[12] =(Button) findViewById(R.id.button12);
            button[13] =(Button) findViewById(R.id.button13);
            button[14] =(Button) findViewById(R.id.button14);
            button[15] =(Button) findViewById(R.id.button15);
            button[16] =(Button) findViewById(R.id.button16);
            button[17] =(Button) findViewById(R.id.button17);
            button[18] =(Button) findViewById(R.id.button18);
            button[19] =(Button) findViewById(R.id.button19);

            for(int i = 0; i < 17; i++)
                button[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view)
                    {
                        addText(((Button) view).getText().toString());
                    }
                });
            button[17].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view)
                {
                    if(!editText.getText().toString().isEmpty())
                        clearSymbol();
                }
            });
            button[18].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view)
                {
                    editText.setText("");
                }
            });
            button[19].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view)
                {
                    editText.setText(checkPoints(editText.getText().toString()));
                    textView.setText(editText.getText().toString());
                    editText.setText(unpack(execute(editText.getText().toString())));
                }
            });
        }
    }
}

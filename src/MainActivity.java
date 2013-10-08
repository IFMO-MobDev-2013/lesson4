import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.example.lesson4.R;

/**
 * Created with IntelliJ IDEA.
 * User: Руслан
 * Date: 09.10.13
 * Time: 0:22
 * To change this template use File | Settings | File Templates.
 */
public class MainActivity extends Activity {
    String input = "";
    boolean button_lock = false;
    TextView text;
    int i;

    private void operationButtonClicked(char operation){
        if (!button_lock){
            input += operation;
            button_lock = true;
            text.append(" " + operation + " ");
        }

    }

    private void numericButtonClicked(char num){
        input += num;
        button_lock = false;
        text.append("" + num);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Button[] numerics = new Button[10];
        numerics[0] = (Button) findViewById(R.id.num0Button);
        numerics[1] = (Button) findViewById(R.id.num1Button);
        numerics[2] = (Button) findViewById(R.id.num2Button);
        numerics[3] = (Button) findViewById(R.id.num3Button);
        numerics[4] = (Button) findViewById(R.id.num4Button);
        numerics[5] = (Button) findViewById(R.id.num5Button);
        numerics[6] = (Button) findViewById(R.id.num6Button);
        numerics[7] = (Button) findViewById(R.id.num7Button);
        numerics[8] = (Button) findViewById(R.id.num8Button);
        numerics[9] = (Button) findViewById(R.id.num9Button);

        Button plusButton = (Button)findViewById(R.id.plusButton);
        Button subtracktButton = (Button)findViewById(R.id.subtractButton);
        Button timesButton = (Button) findViewById(R.id.timesButton);
        Button divisionButton = (Button) findViewById(R.id.timesButton);
        Button dotButton = (Button) findViewById(R.id.timesButton);
        Button leftScopeButton = (Button) findViewById(R.id.leftScopeButton);
        Button rightScopeButton = (Button) findViewById(R.id.rightScopeButton);
        Button resetButton = (Button) findViewById(R.id.resetButton);
        Button deleteButton = (Button) findViewById(R.id.deleteButton);
        Button unaryOpButton = (Button) findViewById(R.id.unaryOpButton);
        text = (TextView) findViewById(R.id.textView);

        plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operationButtonClicked('+');
            }
        });

        subtracktButton.setOnClickListener(new View.OnClickListener() {
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

        for(i = 0; i < 10; i++){
            numerics[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    numericButtonClicked((char)(i + '0'));
                }
            });
        }

    }
}
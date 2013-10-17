package ru.marsermd.Swipylator.GUI;

import android.content.Context;
import android.os.Vibrator;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import ru.marsermd.Swipylator.R;

/**
 * Created with IntelliJ IDEA.
 * User: marsermd
 * Date: 10.10.13
 * Time: 23:26
 * To change this template use File | Settings | File Templates.
 */
public class SwipeView extends RelativeLayout implements View.OnClickListener{

    private static final int SWIPE_MIN_DISTANCE = 120;
    private static final int SWIPE_MAX_OFF_PATH = 450;
    private static final int SWIPE_THRESHOLD_VELOCITY = 100;
    private GestureDetector gestureDetector;
    private int defaultBackgroundColor;
    private View.OnTouchListener gestureListener;

    private View view;
    private Vibrator myVib;

    public TextView topText, middleText, bottomText;
    public Runner topRunner, middleRunner, bottomRunner;

    public SwipeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        view = layoutInflater.inflate(R.layout.swipe_view_layout, this);
        topText = (TextView)findViewById(R.id.topText);
        middleText = (TextView)findViewById(R.id.middleText);
        bottomText = (TextView)findViewById(R.id.bottomText);

        myVib = (Vibrator) context.getSystemService(context.VIBRATOR_SERVICE);

        Runner emptyRunner = new Runner() {
            @Override
            public void execute() {
            }
        };

        topRunner = emptyRunner;
        middleRunner = emptyRunner;
        bottomRunner = emptyRunner;

        defaultBackgroundColor = R.color.swipe_background;

        gestureDetector = new GestureDetector(context, new MyGestureDetector());
        gestureListener = new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {

                switch(event.getAction()) {

                    case MotionEvent.ACTION_DOWN:
                        view.setBackgroundColor(R.color.swipe_touched_background);
                        break;
                    case MotionEvent.ACTION_UP:
                        view.setBackgroundColor(defaultBackgroundColor);
                        break;
                }

                return gestureDetector.onTouchEvent(event);
            }
        };

        setOnClickListener(this);
        setOnTouchListener(gestureListener);
    }




    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }

    @Override
    public void onClick(View v) {
        middleRunner.execute();
        myVib.vibrate(50);
    }


    class MyGestureDetector extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            try {
                if (Math.abs(e1.getY() - e2.getY()) > SWIPE_MAX_OFF_PATH)
                    return false;
                // right to left swipe
                if(e1.getY() - e2.getY() > SWIPE_MIN_DISTANCE && Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY) {
                    topRunner.execute();
                    myVib.vibrate(50);
                }  else if (e2.getY() - e1.getY() > SWIPE_MIN_DISTANCE && Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY) {
                    bottomRunner.execute();
                    myVib.vibrate(50);
                }
            } catch (Exception e) {
                // nothing
            }
            return false;
        }
    }
}

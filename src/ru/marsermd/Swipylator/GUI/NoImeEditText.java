package ru.marsermd.Swipylator.GUI;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * Created with IntelliJ IDEA.
 * User: marsermd
 * Date: 11.10.13
 * Time: 16:13
 * To change this template use File | Settings | File Templates.
 */
public class NoImeEditText extends EditText {
    public NoImeEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {

        super.onFocusChanged(focused, direction, previouslyFocusedRect);    //To change body of overridden methods use File | Settings | File Templates.
    }
}

package ru.marsermd.Swipylator.GUI;

import android.app.Activity;
import android.widget.TextView;
import ru.marsermd.Swipylator.R;

/**
 * Created with IntelliJ IDEA.
 * User: marsermd
 * Date: 11.10.13
 * Time: 1:53
 * To change this template use File | Settings | File Templates.
 */
public abstract class SwipeViewSetter {
    protected abstract void setterExecute(String s);
    private String _top, _mid, _bot;

    public SwipeViewSetter (Activity parent, int id, String top, String mid, String bot) {
        this._top = top;
        this._mid = mid;
        this._bot = bot;
        SwipeView swipeView = (SwipeView) parent.findViewById(id);
        swipeView.topText.setText(top);
        swipeView.bottomText.setText(bot);
        swipeView.middleText.setText(mid);

        swipeView.topRunner = new Runner() {
            @Override
            public void execute() {
                setterExecute(_top);
            }
        };

        swipeView.bottomRunner = new Runner() {
            @Override
            public void execute() {
                setterExecute(_bot);
            }
        };

        swipeView.middleRunner = new Runner() {
            @Override
            public void execute() {
                setterExecute(_mid);
            }
        };
    }
}

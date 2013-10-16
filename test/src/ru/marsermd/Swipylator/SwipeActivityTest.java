package ru.marsermd.Swipylator;

import android.test.ActivityInstrumentationTestCase2;

/**
 * This is a simple framework for a test of an Application.  See
 * {@link android.test.ApplicationTestCase ApplicationTestCase} for more information on
 * how to write and extend Application tests.
 * <p/>
 * To run this test, you can type:
 * adb shell am instrument -w \
 * -e class ru.marsermd.Swipylator.SwipeActivityTest \
 * ru.marsermd.Swipylator.tests/android.test.InstrumentationTestRunner
 */
public class SwipeActivityTest extends ActivityInstrumentationTestCase2<SwipeActivity> {

    public SwipeActivityTest() {
        super("ru.marsermd.Swipylator", SwipeActivity.class);
    }

}

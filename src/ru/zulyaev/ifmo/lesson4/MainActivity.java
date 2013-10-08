package ru.zulyaev.ifmo.lesson4;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v13.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

/**
 * @author Никита
 */
public class MainActivity extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        ViewPager pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(new PagesAdapter(getFragmentManager()));
    }

    static class PagesAdapter extends FragmentStatePagerAdapter {
        public PagesAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            switch (i) {
                case 0: return new BasicFragment();
                case 1 : return new AdvancedFragment();
            }
            return null;
        }

        @Override
        public int getCount() {
            return 2;
        }
    }
}
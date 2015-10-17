package com.rexicore.test.eejhey.volleyrot;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Eddie on 1/7/2015.
 */
public class CustomPagerAdapter extends FragmentPagerAdapter {
    final int PAGE_COUNT = 2;

    public CustomPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch( i ){
            case 0:
                RotationFrag rotationFrag = new RotationFrag();
                return rotationFrag;
            case 1:
                ScoreboardFrag scoreboardFrag = new ScoreboardFrag();
                return scoreboardFrag;
        }

        return null;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Rotation";
            case 1:
                return "Scoreboard";
        }

        return null;
    }
}

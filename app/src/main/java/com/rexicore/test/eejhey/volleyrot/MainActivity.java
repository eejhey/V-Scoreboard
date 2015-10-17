package com.rexicore.test.eejhey.volleyrot;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.rexicore.test.eejhey.common.slidingtabs.SlidingTabLayout;


public class MainActivity extends ActionBarActivity {
    private SamplePagerAdapter mSPA;
    private ViewPager viewpager;
    private SlidingTabLayout mSTL;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById( R.id.app_bar );
        setSupportActionBar( toolbar );
        mSPA = new SamplePagerAdapter( getSupportFragmentManager() );

        viewpager = (ViewPager) findViewById( R.id.viewpager );
        viewpager.setAdapter( mSPA );

        mSTL = (SlidingTabLayout) findViewById( R.id.sliding_tabs );
        mSTL.setViewPager( viewpager );
        mSTL.setCustomTabColorizer( new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                switch( position ){
                    case 0:
                        return Color.parseColor( "#99CC00" );
                    case 1:
                        return Color.parseColor( "#78C3FB" );
                }
                return 0;
            }

            @Override
            public int getDividerColor(int position) {
                return Color.GRAY;
            }
        });
    }

    class SamplePagerAdapter extends FragmentPagerAdapter {

        public SamplePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        /**
         * @return the number of pages to display
         */
        @Override
        public int getCount() {
            return 2;
        }

        /**
         * @return true if the value returned from {@link #instantiateItem(android.view.ViewGroup, int)} is the
         * same object as the {@link android.view.View} added to the {@link ViewPager}.
         */
        /*@Override
        public boolean isViewFromObject(View view, Object o) {
            return o == view;
        }*/

        // BEGIN_INCLUDE (pageradapter_getpagetitle)

        /**
         * Return the title of the item at {@code position}. This is important as what this method
         * returns is what is displayed in the {@link com.rexicore.test.eejhey.common.slidingtabs.SlidingTabLayout}.
         * <p/>
         * Here we construct one using the position value, but for real application the title should
         * refer to the item's contents.
         */
        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return getString(R.string.rotation);
                case 1:
                    return getString(R.string.scoreboard);
            }
            return "Error";
        }
        // END_INCLUDE (pageradapter_getpagetitle)

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;
            switch( position ) {
                case 0:
                    fragment = new RotationFrag();
                    break;
                case 1:
                    fragment = new ScoreboardFrag();
                    break;
            }
            return fragment;
        }

        /**
         * Instantiate the {@link View} which should be displayed at {@code position}. Here we
         * inflate a layout from the apps resources and then change the text view to signify the position.
         */
        /*@Override
        public Object instantiateItem(ViewGroup container, int position) {
            // Inflate a new layout from our resources
            Fragment fragment = null;
            View view = null;
            switch (position) {
                case 0:
                    view = getLayoutInflater().inflate( R.layout.fragment_rotation, container, false );
                    fragment = new RotationFrag();
                    //view = fragment.getView();
                    break;
                case 1:
                    view = getLayoutInflater().inflate( R.layout.fragment_scoreboard, container, false );
                    fragment = new ScoreboardFrag();
                    //view = fragment.getView();
                    break;
            }
            // Add the newly created View to the ViewPager
            container.addView(view);

            // Retrieve a TextView from the inflated View, and update it's text
            /*TextView title = (TextView) view.findViewById(R.id.item_title);
            title.setText(String.valueOf(position + 1));*/

            //Log.i(LOG_TAG, "instantiateItem() [position: " + position + "]");

            // Return the View
            /*return view;
        }*/

        /**
         * Destroy the item from the {@link ViewPager}. In our case this is simply removing the
         * {@link View}.
         */
        /*@Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
            //Log.i(LOG_TAG, "destroyItem() [position: " + position + "]");
        }*/

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

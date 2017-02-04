package com.workshopbuildingui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.astuetz.PagerSlidingTabStrip;
import com.workshopbuildingui.ICanNavigateActivity;
import com.workshopbuildingui.PageFragment;
import com.workshopbuildingui.R;
import com.workshopbuildingui.fragments.tabs.SearchFragment;
import com.workshopbuildingui.fragments.tabs.SuperheroesFragment;
import com.workshopbuildingui.models.Superhero;

public class MainActivity extends AppCompatActivity implements ICanNavigateActivity<Superhero> {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get the ViewPager and set it's PagerAdapter so that it can display items
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new SampleFragmentPagerAdapter(getSupportFragmentManager()));

        // Give the PagerSlidingTabStrip the ViewPager
        PagerSlidingTabStrip tabsStrip = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        // Attach the view pager to the tab strip
        tabsStrip.setViewPager(viewPager);
    }

    @Override
    public void navigate(Superhero superhero) {
        Intent intent = new Intent(this, SuperheroDetailsActivity.class);

        intent.putExtra(SuperheroDetailsActivity.SUPERHERO_KEY, superhero);
        this.startActivity(intent);
    }

    public class SampleFragmentPagerAdapter extends FragmentStatePagerAdapter {
        final int PAGE_COUNT = 4;
        private String tabTitles[] = new String[] { "Superheroes", "Factions", "Search", "About" };

        public SampleFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return PAGE_COUNT;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    return new SuperheroesFragment();
                case 2:
                    return new SearchFragment();
                default:
                    return PageFragment.createFragment(position + 1);
            }
        }

        @Override
        public CharSequence getPageTitle(int position) {
            // Generate title based on item position
            return tabTitles[position];
        }
    }

}
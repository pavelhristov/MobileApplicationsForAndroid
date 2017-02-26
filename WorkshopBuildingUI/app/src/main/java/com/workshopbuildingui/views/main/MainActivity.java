package com.workshopbuildingui.views.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.astuetz.PagerSlidingTabStrip;
import com.data.HttpData;
import com.data.base.BaseData;
import com.workshopbuildingui.ICanNavigateActivity;
import com.workshopbuildingui.PageFragment;
import com.workshopbuildingui.R;
import com.workshopbuildingui.views.details.SuperheroDetailsActivity;
import com.workshopbuildingui.views.main.tabs.search.SearchContracts;
import com.workshopbuildingui.views.main.tabs.search.SearchFragment;
import com.workshopbuildingui.views.main.tabs.search.SearchPresenter;
import com.workshopbuildingui.views.main.tabs.superheroes.SuperheroesContracts;
import com.workshopbuildingui.views.main.tabs.superheroes.SuperheroesFragment;
import com.data.models.Superhero;
import com.workshopbuildingui.views.main.tabs.superheroes.SuperheroesPresenter;

public class MainActivity extends AppCompatActivity implements ICanNavigateActivity<Superhero> {


    private SearchContracts.Presenter searchPresenter;
    private SearchContracts.View searchView;
    private SuperheroesContracts.View superheroesView;
    private SuperheroesContracts.Presenter superheroesPresenter;
    private BaseData<Superhero> httpData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String apiUrl = "http://androidteamworkwebapi.azurewebsites.net/api/superheroes/";
        this.httpData = new HttpData<>(apiUrl, Superhero.class, Superhero[].class);

        this.searchView = new SearchFragment();
        this.searchPresenter = new SearchPresenter(this.searchView, this.httpData);

        this.superheroesView = new SuperheroesFragment();
        this.superheroesPresenter = new SuperheroesPresenter(this.superheroesView, this.httpData);


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
                    return (Fragment)superheroesPresenter.getView();
                case 2:
                    return (Fragment)searchPresenter.getView();
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

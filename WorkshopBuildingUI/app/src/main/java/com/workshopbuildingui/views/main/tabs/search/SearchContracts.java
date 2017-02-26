package com.workshopbuildingui.views.main.tabs.search;

import android.widget.ArrayAdapter;

import com.data.models.Superhero;

import java.util.List;

public class SearchContracts {
    public interface View{
        void setPresenter(Presenter presenter);

        void setSuperheroes(List<Superhero> superheroes);
    }

    public interface Presenter{
        View getView();
        void searchByPattern(String pattern);
        Superhero getSuperhero(int position);
    }
}

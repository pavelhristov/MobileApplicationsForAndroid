package com.workshopbuildingui.views.main.tabs.superheroes;

import com.data.models.Superhero;

import java.util.List;

public class SuperheroesContracts {
    public interface View{
        void setPresenter(Presenter presenter);

        void setSuperheroes(List<Superhero> superheroes);
    }

    public interface Presenter{
        void start();

        View getView();

        Superhero getSuperhero(int position);
    }
}

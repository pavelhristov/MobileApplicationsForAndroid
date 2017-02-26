package com.workshopbuildingui.views.details;

import com.data.models.Superhero;

public class SuperheroDetailsContracts {
    public interface View{
        void setPresenter(Presenter presenter);

        void setSuperhero(Superhero superhero);
    }

    public interface Presenter{
        View getView();

        void setSuperhero(Superhero superhero);

        void start();
    }
}

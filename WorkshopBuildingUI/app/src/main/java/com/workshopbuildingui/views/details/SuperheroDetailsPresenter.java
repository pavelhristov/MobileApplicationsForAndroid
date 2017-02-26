package com.workshopbuildingui.views.details;

import com.data.models.Superhero;

public class SuperheroDetailsPresenter implements SuperheroDetailsContracts.Presenter {
    private final SuperheroDetailsContracts.View view;
    private Superhero superhero;

    public SuperheroDetailsPresenter(SuperheroDetailsContracts.View view){
        this.view = view;
        this.getView().setPresenter(this);
    }

    @Override
    public SuperheroDetailsContracts.View getView() {
        return this.view;
    }

    @Override
    public void setSuperhero(Superhero superhero) {
        this.superhero = superhero;
    }

    @Override
    public void start() {
        this.view.setSuperhero(this.superhero);
    }
}

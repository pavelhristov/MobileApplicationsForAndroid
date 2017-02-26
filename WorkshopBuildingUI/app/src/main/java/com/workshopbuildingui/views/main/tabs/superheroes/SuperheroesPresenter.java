package com.workshopbuildingui.views.main.tabs.superheroes;

import com.data.base.BaseData;
import com.data.models.Superhero;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SuperheroesPresenter implements SuperheroesContracts.Presenter {
    private final SuperheroesContracts.View view;
    private final BaseData<Superhero> superheroData;
    private final List<Superhero> superheroes;

    public SuperheroesPresenter(SuperheroesContracts.View view,
                                BaseData<Superhero> superheroData){
        this.view = view;
        this.superheroData = superheroData;
        this.getView().setPresenter(this);
        this.superheroes = new ArrayList<>();
    }


    @Override
    public void start() {
        this.superheroData.getAll().subscribe(superheroesHttp ->{
            superheroes.clear();
            superheroes.addAll(new ArrayList<>(Arrays.asList((Superhero[]) superheroesHttp)));
            this.getView().setSuperheroes(superheroes);
        });
    }

    @Override
    public SuperheroesContracts.View getView() {
        return this.view;
    }

    @Override
    public Superhero getSuperhero(int position) {
        return this.superheroes.get(position);
    }
}

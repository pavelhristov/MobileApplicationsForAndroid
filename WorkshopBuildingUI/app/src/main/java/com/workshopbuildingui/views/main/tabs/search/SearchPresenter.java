package com.workshopbuildingui.views.main.tabs.search;

import com.data.base.BaseData;
import com.data.models.Superhero;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SearchPresenter implements SearchContracts.Presenter {
    private final SearchContracts.View view;
    private final BaseData superheroData;
    private final List<Superhero> superheroes;

    public SearchPresenter(SearchContracts.View view,
                           BaseData<Superhero> superheroData){

        this.superheroes = new ArrayList<>();
        this.view = view;
        this.getView().setPresenter(this);
        this.superheroData = superheroData;
    }

    @Override
    public SearchContracts.View getView() {
        return this.view;
    }

    @Override
    public void searchByPattern(String pattern) {
        superheroData.search(pattern).subscribe(superheroesHttp -> {
            superheroes.clear();
            superheroes.addAll(new ArrayList<>(Arrays.asList((Superhero[]) superheroesHttp)));
            this.getView().setSuperheroes(superheroes);
        });
    }

    @Override
    public Superhero getSuperhero(int position) {
        return this.superheroes.get(position);
    }
}

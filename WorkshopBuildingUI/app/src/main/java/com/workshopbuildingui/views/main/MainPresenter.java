package com.workshopbuildingui.views.main;

public class MainPresenter implements MainContracts.Presenter{
    private final MainContracts.View view;

    public MainPresenter(MainContracts.View view){
        this.view = view;
        this.getView().setPresenter(this);
    }

    @Override
    public MainContracts.View getView() {
        return this.view;
    }
}

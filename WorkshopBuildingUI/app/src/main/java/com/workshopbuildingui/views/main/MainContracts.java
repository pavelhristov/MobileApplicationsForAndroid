package com.workshopbuildingui.views.main;

public class MainContracts {
    public interface View{
        void setPresenter(Presenter presenter);
    }

    public interface Presenter{
        View getView();
    }
}

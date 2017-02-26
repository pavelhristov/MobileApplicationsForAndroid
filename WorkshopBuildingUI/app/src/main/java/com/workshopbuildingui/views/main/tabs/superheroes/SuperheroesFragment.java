package com.workshopbuildingui.views.main.tabs.superheroes;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.data.HttpData;
import com.data.base.BaseData;
import com.workshopbuildingui.ICanNavigateActivity;
import com.workshopbuildingui.R;
import com.data.models.Superhero;

import java.util.List;


public class SuperheroesFragment extends Fragment implements SuperheroesContracts.View, AdapterView.OnItemClickListener {

    private ArrayAdapter<Superhero> superheroesAdapter;
    private ListView lvSuperheroes;
    private Context ctx;
    private SuperheroesContracts.Presenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_superheroes, container, false);
        this.lvSuperheroes = (ListView) root.findViewById(R.id.fragment_superheroes_list);
        this.ctx = root.getContext();
        this.lvSuperheroes.setOnItemClickListener(this);

        this.presenter.start();

        return root;
    }

    @Override
    public void setPresenter(SuperheroesContracts.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void setSuperheroes(List<Superhero> superheroes) {
        this.superheroesAdapter =
                new ArrayAdapter<Superhero>(ctx, -1, superheroes) {
                    @NonNull
                    @Override
                    public View getView(int position, View convertView, ViewGroup parent) {
                        View view = convertView;
                        if (view == null) {
                            LayoutInflater inflater = LayoutInflater.from(this.getContext());
                            view = inflater.inflate(R.layout.item_superhero, parent, false);
                        }

                        TextView tvTitle = (TextView) view.findViewById(R.id.tv_superhero);

                        String title = this.getItem(position).name;
                        tvTitle.setText(title);

                        return view;
                    }
                };

        this.lvSuperheroes.setAdapter(superheroesAdapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Superhero sh = this.presenter.getSuperhero(position);
        ICanNavigateActivity<Superhero> activity = (ICanNavigateActivity<Superhero>) this.getActivity();

        activity.navigate(sh);
    }
}

package com.workshopbuildingui.fragments.tabs;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.data.HttpData;
import com.data.base.BaseData;
import com.workshopbuildingui.ICanNavigateActivity;
import com.workshopbuildingui.R;
import com.workshopbuildingui.data.Data;
import com.workshopbuildingui.models.Superhero;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment {

    public static final String ARG_PAGE = "ARG_PAGE";
    private BaseData<Superhero> superheroData;
    private Data data;

    public SearchFragment() {
        // Required empty public constructor
    }


    public static SuperheroesFragment createFragment(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        SuperheroesFragment fragment = new SuperheroesFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_search, container, false);
        ListView lvSuperheroes = (ListView) root.findViewById(R.id.fragment_superheroes_list);
        final List<Superhero> superheroes = new ArrayList<>();
        //superheroData = new HttpData<Superhero>("",Superhero.class,Superhero[].class);
        data = new Data();

        Button btnSearch = (Button)root.findViewById(R.id.btn_search);
        btnSearch.setOnClickListener((btn) -> {
            String pattern = ((TextView)root.findViewById(R.id.et_search_pattern)).getText().toString();
            List<Superhero> foundSuperheroes = data.search(pattern);
            ArrayAdapter<Superhero> superheroesAdapter =
                    new ArrayAdapter<Superhero>(root.getContext(), -1, foundSuperheroes) {
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
            superheroes.clear();
            superheroes.addAll(foundSuperheroes);
            lvSuperheroes.setAdapter(superheroesAdapter);
        });

        lvSuperheroes.setOnItemClickListener((parent, view, position, id) -> {
            Superhero sh = superheroes.get(position);
            //if activity is not ICanNavigateActivity) then do nothing
            ICanNavigateActivity<Superhero> activity = (ICanNavigateActivity<Superhero>) this.getActivity();

            activity.navigate(sh);
        });


        return root;
    }

}

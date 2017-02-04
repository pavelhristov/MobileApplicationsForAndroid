package com.workshopbuildingui.fragments.tabs;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.workshopbuildingui.ICanNavigateActivity;
import com.workshopbuildingui.R;
import com.workshopbuildingui.data.Data;
import com.workshopbuildingui.models.Superhero;

import java.util.List;


public class SuperheroesFragment extends Fragment {
    /*public static final String ARG_PAGE = "ARG_PAGE";

    public static SuperheroesFragment createFragment(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        SuperheroesFragment fragment = new SuperheroesFragment();
        fragment.setArguments(args);
        return fragment;
    }*/

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_superheroes, container, false);
        ListView lvSuperheroes = (ListView) root.findViewById(R.id.fragment_superheroes_list);
        Data data = new Data();
        final List<Superhero> superheroes = data.getSuperheroes();

        ArrayAdapter<Superhero> superheroesAdapter =
                new ArrayAdapter<Superhero>(root.getContext(), -1, superheroes) {
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

        lvSuperheroes.setAdapter(superheroesAdapter);

        lvSuperheroes.setOnItemClickListener((parent, view, position, id) -> {
            Superhero sh = superheroes.get(position);
            //if activity is not ICanNavigateActivity) then do nothing
            ICanNavigateActivity<Superhero> activity = (ICanNavigateActivity<Superhero>) this.getActivity();

            activity.navigate(sh);
        });

        return root;
    }
}

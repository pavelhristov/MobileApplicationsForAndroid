package com.workshopbuildingui.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.workshopbuildingui.R;
import com.workshopbuildingui.models.Superhero;

import java.io.Serializable;

import static com.workshopbuildingui.activities.SuperheroDetailsActivity.SUPERHERO_KEY;

public class SuperheroDetailsFragment extends Fragment {


    private Superhero superhero;

    public SuperheroDetailsFragment() {
        // Required empty public constructor
    }


    public static SuperheroDetailsFragment createFragment(Superhero superhero) {
        Bundle bundle = new Bundle();

        bundle.putSerializable(SUPERHERO_KEY, superhero);

        SuperheroDetailsFragment fragment = new SuperheroDetailsFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root =  inflater.inflate(R.layout.fragment_superhero_details, container, false);

        Bundle arguments = this.getArguments();
        Serializable serializedBook = null;

        if (arguments != null) {
            serializedBook = arguments.getSerializable(SUPERHERO_KEY);
        }

        if (serializedBook != null) {
            this.setSuperhero(root, (Superhero) serializedBook);
        }


        return root;
    }
    protected void setSuperhero(View view, Superhero superhero) {
        this.superhero = superhero;
        ((TextView) view.findViewById(R.id.tv_name))
                .setText(this.superhero.name);

        ((TextView) view.findViewById(R.id.tv_secret_identity))
                .setText(this.superhero.secretIdentity);
    }

    public void setSuperhero(Superhero superhero) {
        this.setSuperhero(this.getView(), superhero);
    }


}

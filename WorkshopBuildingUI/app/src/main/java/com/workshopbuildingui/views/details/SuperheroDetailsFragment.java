package com.workshopbuildingui.views.details;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.workshopbuildingui.R;
import com.data.models.Superhero;


import static com.workshopbuildingui.views.details.SuperheroDetailsActivity.SUPERHERO_KEY;

public class SuperheroDetailsFragment extends Fragment implements SuperheroDetailsContracts.View {


    private SuperheroDetailsContracts.Presenter presenter;
    private TextView tvName;
    private TextView tvSecretIdentity;

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

        this.tvName = (TextView) root.findViewById(R.id.tv_name);
        this.tvSecretIdentity = (TextView) root.findViewById(R.id.tv_secret_identity);

        this.presenter.start();

        return root;
    }

    @Override
    public void setSuperhero( Superhero superhero) {
        this.tvName
                .setText(superhero.name);

        this.tvSecretIdentity
                .setText(superhero.secretIdentity);
    }



    @Override
    public void setPresenter(SuperheroDetailsContracts.Presenter presenter) {
        this.presenter = presenter;
    }
}

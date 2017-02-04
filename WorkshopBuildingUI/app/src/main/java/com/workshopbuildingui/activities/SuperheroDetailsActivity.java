package com.workshopbuildingui.activities;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.workshopbuildingui.R;
import com.workshopbuildingui.fragments.SuperheroDetailsFragment;
import com.workshopbuildingui.models.Superhero;

public class SuperheroDetailsActivity extends AppCompatActivity {
    public static final String SUPERHERO_KEY = "superhero";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_superhero_details);

        Intent intent = this.getIntent();

        Superhero superhero = (Superhero) intent.getSerializableExtra(SUPERHERO_KEY);

        Fragment fragment = SuperheroDetailsFragment.createFragment(superhero);

        this.getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container_fragment, fragment)
                .commit();
    }
}

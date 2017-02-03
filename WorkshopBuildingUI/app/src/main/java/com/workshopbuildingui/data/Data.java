package com.workshopbuildingui.data;

import com.workshopbuildingui.models.Superhero;

import java.util.ArrayList;
import java.util.List;

public class Data {
    static ArrayList<Superhero> superheroes;

    static {
        superheroes = new ArrayList<Superhero>();

        superheroes.add(new Superhero("Batman","Bruce Wayne","1"));
        superheroes.add(new Superhero("Iron Man","Tony Stark","2"));
        superheroes.add(new Superhero("Thor","Thor","3"));
    }

    public List<Superhero> getSuperheroes() {
        return new ArrayList<>(superheroes);
    }

    public Superhero getSuperheroById(String id) {
        for (Superhero superhero: superheroes) {
            if (superhero.id.equals(id)) {
                return superhero;
            }
        }

        return null;
    }

    public List<Superhero> search(String pattern){
        ArrayList<Superhero> list = new ArrayList<>();
        pattern = pattern.toLowerCase();

        for (Superhero sh:superheroes) {
            if (sh.secretIdentity.toLowerCase().contains(pattern) ||
                    sh.name.toLowerCase().contains(pattern)){
                list.add(sh);
            }
        }

        return list;
    }
}

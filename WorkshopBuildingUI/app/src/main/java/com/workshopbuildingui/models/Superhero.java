package com.workshopbuildingui.models;

import java.io.Serializable;

public class Superhero implements Serializable {
    public String name;
    public String secretIdentity;
    public String id;

    public Superhero(String name, String secretIdentity, String id){
        this.name= name;
        this.secretIdentity = secretIdentity;
        this.id = id;
    }
}

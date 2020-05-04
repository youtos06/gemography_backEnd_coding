package com.githubtest.gitrepos.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;



@JsonIgnoreProperties(ignoreUnknown = true)
public class Items {
    private ArrayList<Repository> items;

    public Items() {
        this.items = new ArrayList<>();
    }


    public ArrayList<Repository> getItems() {
        return items;
    }


    public void setItems(ArrayList<Repository> items) {
        this.items = items;
    }

    public void addItems(ArrayList<Repository> tempItems){
        this.items.addAll(tempItems);
    }
}

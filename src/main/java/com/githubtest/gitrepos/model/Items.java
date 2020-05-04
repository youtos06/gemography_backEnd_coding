package com.githubtest.gitrepos.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

import java.util.ArrayList;
import java.util.List;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Items {
    @Id
    @JsonIgnore
    private Long id;

    @JsonIgnore
    private String date;


    @Transient
    @JsonInclude()
    private ArrayList<GitRepo> items;

    public Items() {
        this.items = new ArrayList<>();
    }

    public String getDate() {
        return date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ArrayList<GitRepo> getItems() {
        return items;
    }



    public void setItems(ArrayList<GitRepo> items) {
        this.items= items;
    }

    public void addItems(List<GitRepo> tempItems){

        this.items.addAll(tempItems);
    }

    public void addItems(ArrayList<GitRepo> tempItems){
        this.items.addAll(tempItems);
    }
}

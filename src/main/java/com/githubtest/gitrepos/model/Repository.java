package com.githubtest.gitrepos.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Repository {
    private long id;
    private String node_id;
    private String name;
    private String full_name;
    private String created_at;
    private String language;

    public Repository() {
    }

    public Repository(long id, String node_id, String name, String full_name, String created_at, String language) {
        this.id = id;
        this.node_id = node_id;
        this.name = name;
        this.full_name = full_name;
        this.created_at = created_at;
        this.language = language;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNode_id() {
        return node_id;
    }

    public void setNode_id(String node_id) {
        this.node_id = node_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}

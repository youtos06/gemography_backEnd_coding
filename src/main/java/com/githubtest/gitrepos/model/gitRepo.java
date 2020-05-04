package com.githubtest.gitrepos.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class gitRepo {
    @Id
    private Long id;
    private String node_id;
    private String name;
    private String full_name;
    private String created_at;
    private String language;

    public gitRepo() {
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

    @Override
    public String toString() {
        return "gitRepo{" +
                "id=" + id +
                ", node_id='" + node_id + '\'' +
                ", name='" + name + '\'' +
                ", full_name='" + full_name + '\'' +
                ", created_at='" + created_at + '\'' +
                ", language='" + language + '\'' +
                '}';
    }
}

package com.githubtest.gitrepos.model.dto;

import com.githubtest.gitrepos.model.GitRepo;

import java.util.ArrayList;

public class ItemsDto {
    private String language;
    private String date;
    private int numberOfRepositories;
    private ArrayList<GitRepo> languageRepositories;

    public ItemsDto() {
        this.languageRepositories = new ArrayList<>();
    }

    public ItemsDto(String language, String date, int numberOfRepositories, ArrayList<GitRepo> languageRepositories) {
        this.language = language;
        this.date = date;
        this.numberOfRepositories = numberOfRepositories;
        this.languageRepositories = languageRepositories;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getNumberOfRepositories() {
        return numberOfRepositories;
    }

    public void setNumberOfRepositories(int numberOfRepositories) {
        this.numberOfRepositories = numberOfRepositories;
    }

    public ArrayList<GitRepo> getLanguageRepositories() {
        return languageRepositories;
    }

    public void setLanguageRepositories(ArrayList<GitRepo> languageRepositories) {
        this.languageRepositories = languageRepositories;
    }

    public void addNewRepository(GitRepo gitRepo){
        this.languageRepositories.add(gitRepo);
    }
}

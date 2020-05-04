package com.githubtest.gitrepos.consumingRest;


import com.githubtest.gitrepos.model.Items;
import com.githubtest.gitrepos.model.GitRepo;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

@Component
public class ConsumingGitReposRest {
    public RestTemplate restTemplate = new RestTemplate();
    public SimpleDateFormat df = new SimpleDateFormat("YYYY-MM-dd");

    // since new api v3 get only 29 repo per page i'll be only limitted to using the first 3 pages

    /**
     * we make three calls for the api since the resent change only bring 29 repo
     * @param dueudate
     * @return most famous repos of first 3 pages
     */
    public Items getMostFamousRepository(String dueudate) {


        Items famousRepositories = new Items();
        famousRepositories.addItems(consumGitRestReposApiPage(1, dueudate));
        famousRepositories.addItems(consumGitRestReposApiPage(2, dueudate));
        famousRepositories.addItems(consumGitRestReposApiPage(3, dueudate));
        return famousRepositories;
    }


    public ArrayList<GitRepo> consumGitRestReposApiPage(int page, String date) {
        String gitReposApiUrl = "https://api.github.com/search/repositories?q=created:>" + date + "&sort=stars&order=desc&page=" + page;
        // we don't verify the results since we totally control the communication with the api from our side
        // probability of git server going down is small
        Items tempItems = restTemplate.getForObject(gitReposApiUrl, Items.class);
        System.out.println("git api page call :"+gitReposApiUrl);
        return tempItems.getItems();
    }
}

package com.githubtest.gitrepos.consumingRest;


import com.githubtest.gitrepos.model.Items;
import com.githubtest.gitrepos.model.Repository;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

@Component
public class ConsumingGitReposRest {
    public RestTemplate restTemplate = new RestTemplate();
    public SimpleDateFormat df = new SimpleDateFormat("YYYY-MM-dd");

    // since new api v3 get only 29 repo per page i'll be only limitted to using the first 3 pages

    public Items getMostFamousRepository() {
        Calendar c1 = Calendar.getInstance();
        c1.add(Calendar.DAY_OF_YEAR, -30);
        df = new SimpleDateFormat("yyyy-MM-dd");
        Date resultdate = new Date(c1.getTimeInMillis());
        String dueudate = df.format(resultdate);

        Items famousRepositories = new Items();
        famousRepositories.addItems(consumGitRestReposApiPage(0, dueudate));
        famousRepositories.addItems(consumGitRestReposApiPage(1, dueudate));
        famousRepositories.addItems(consumGitRestReposApiPage(2, dueudate));
        return famousRepositories;
    }


    public ArrayList<Repository> consumGitRestReposApiPage(int page, String date) {
        String gitReposApiUrl = "https://api.github.com/search/repositories?q=created:>" + date + "&sort=stars&order=desc&page=" + page;
        // we don't verify the results since we totally control the communication with the api from our side
        // probability of git server going down is small
        Items tempItems = restTemplate.getForObject(gitReposApiUrl, Items.class);
        return tempItems.getItems();
    }
}

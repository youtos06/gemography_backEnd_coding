package com.githubtest.gitrepos.service;


import com.githubtest.gitrepos.consumingRest.ConsumingGitReposRest;
import com.githubtest.gitrepos.dao.ItemsDao;
import com.githubtest.gitrepos.dao.RepositoryDao;
import com.githubtest.gitrepos.model.Items;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Service
public class ItemsService {

    private final ConsumingGitReposRest consumingGitReposRest;
    private RestTemplate restTemplate = new RestTemplate();
    private SimpleDateFormat df = new SimpleDateFormat("YYYY-MM-dd");
    private RepositoryDao repositoryDao;
    private ItemsDao itemsDao;

    public ItemsService(ConsumingGitReposRest consumingGitReposRest, RepositoryDao repositoryDao, ItemsDao itemsDao) {
        this.consumingGitReposRest = consumingGitReposRest;
        this.repositoryDao = repositoryDao;
        this.itemsDao = itemsDao;
    }

    /**
     * in this function we gonna make the contact with git api each day only once -> the first call
     * we verify if the call had already been made -> if so we retrieve data from database
     * -> if not we retrieve data from api and stock it in the database to be used in the next calls
     * this approach ain't always optimal but we minimize the contact with the external call and we can then stock tha data to be used in other treatment
     * -> if the next day we call the function we delete the previous day data
     * @return
     */
    public Items getItemsFromGitApi(){
        Items famousRepositories ;
        Calendar c1 = Calendar.getInstance();
        c1.add(Calendar.DAY_OF_YEAR, -30);
        df = new SimpleDateFormat("yyyy-MM-dd");
        Date resultdate = new Date(c1.getTimeInMillis());
        String dueudate = df.format(resultdate);
        //System.out.println("-------------api call----------------"+(itemsDao.getByDate(dueudate) == null));
        if (itemsDao.getByDate(dueudate) == null){
            this.clearDataBaseForNewDayCall();
            famousRepositories = consumingGitReposRest.getMostFamousRepository(dueudate);
            famousRepositories.getItems().forEach((item) -> {
                //System.out.println(item.toString());
                if(item.getLanguage()!=null){
                    item.setLanguage(item.getLanguage().toLowerCase());
                }else{
                    item.setLanguage("undefined");
                }
                repositoryDao.save(item);
            });
            famousRepositories.setDate(dueudate);
            famousRepositories.setId(0);
            itemsDao.save(famousRepositories);
        }else{
            //System.out.println("-------------enter Database----------------");
            famousRepositories = itemsDao.getByDate(dueudate);
            famousRepositories.addItems(repositoryDao.findAll());
        }

        return famousRepositories;
    }

    public void clearDataBaseForNewDayCall(){
        repositoryDao.deleteAll();
        itemsDao.deleteAll();
    }
}

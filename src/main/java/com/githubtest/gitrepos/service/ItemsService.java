package com.githubtest.gitrepos.service;


import com.githubtest.gitrepos.consumingRest.ConsumingGitReposRest;
import com.githubtest.gitrepos.dao.ItemsDao;
import com.githubtest.gitrepos.dao.RepositoryDao;
import com.githubtest.gitrepos.model.Items;
import com.githubtest.gitrepos.model.dto.ItemsDto;
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
    private final RepositoryDao repositoryDao;
    private final ItemsDao itemsDao;
    private final ItemsDtoFromItemsConverter itemsDtoFromItemsConverter;

    public ItemsService(ConsumingGitReposRest consumingGitReposRest, RepositoryDao repositoryDao, ItemsDao itemsDao, ItemsDtoFromItemsConverter itemsDtoFromItemsConverter) {
        this.consumingGitReposRest = consumingGitReposRest;
        this.repositoryDao = repositoryDao;
        this.itemsDao = itemsDao;
        this.itemsDtoFromItemsConverter = itemsDtoFromItemsConverter;
    }

    /**
     * in this function we gonna make the contact with git api each day only once -> the first call
     * we verify if the call had already been made -> if so we retrieve data from database
     * -> if not we retrieve data from api and stock it in the database to be used in the next calls
     * this approach ain't always optimal but we minimize the contact with the external call and we can then stock tha data to be used in other treatment
     * -> if the next day we call the function we delete the previous day data
     * @return
     */
    public Items getRepositoriesFromGitApiLastMonth(){
        Items famousRepositories ;
        Calendar c1 = Calendar.getInstance();
        c1.add(Calendar.DAY_OF_YEAR, -30);
        df = new SimpleDateFormat("yyyy-MM-dd");
        Date resultdate = new Date(c1.getTimeInMillis());
        String dueudate = df.format(resultdate);
        //System.out.println("-------------api call----------------"+(itemsDao.getByDate(dueudate) == null));
        if (itemsDao.getByDate(dueudate) == null){
            clearDataBaseForNewDayCall();
            famousRepositories = getGitApiService(dueudate);

        }else{
            //System.out.println("-------------enter Database----------------");

            famousRepositories = getGitDatabaseStorage(dueudate);
        }
        return famousRepositories;
    }

    public ItemsDto getRepositoriesAccordingToLanguageFromLastMonth(String language){
        Items items = getRepositoriesFromGitApiLastMonth();
        return itemsDtoFromItemsConverter.convert(items,language);
    }

    // call service and modify data based on apiService
    private Items getGitApiService(String dueudate){
        Items famousRepositories ;
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
        return famousRepositories;
    }
    // get data from the database
    private Items getGitDatabaseStorage(String dueudate){
        Items famousRepositories ;
        famousRepositories = itemsDao.getByDate(dueudate);
        famousRepositories.addItems(repositoryDao.findAll());
        return famousRepositories;
    }

    public void clearDataBaseForNewDayCall(){
        repositoryDao.deleteAll();
        itemsDao.deleteAll();
    }
}

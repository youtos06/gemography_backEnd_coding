package com.githubtest.gitrepos.service;


import com.githubtest.gitrepos.model.GitRepo;
import com.githubtest.gitrepos.model.Items;
import com.githubtest.gitrepos.model.dto.ItemsDto;
import org.springframework.stereotype.Component;


@Component
public class ItemsDtoFromItemsConverter {
    public ItemsDto convert(Items items,String language){
        if(language == null){
            language = "undefined";
        }

        ItemsDto itemsDto = new ItemsDto();
        itemsDto.setLanguage(language);
        itemsDto.setDate(items.getDate());
        for (GitRepo repository : items.getItems()){
            if (repository.getLanguage().equals(language)){
                itemsDto.addNewRepository(repository);
                itemsDto.setNumberOfRepositories((itemsDto.getNumberOfRepositories()+1));
            }
        }

        return itemsDto;
    }

}

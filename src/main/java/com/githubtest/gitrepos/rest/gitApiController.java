package com.githubtest.gitrepos.rest;



import com.githubtest.gitrepos.consumingRest.ConsumingGitReposRest;
import com.githubtest.gitrepos.model.Items;
import com.githubtest.gitrepos.service.ItemsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/gitTrends")
public class gitApiController {


    private final ItemsService itemsService;

    public gitApiController(ItemsService itemsService) {
        this.itemsService = itemsService;
    }

    @GetMapping()
    public ResponseEntity<Items> getTrendRepositoriesForLastMonth(){

        return ResponseEntity.ok().body(itemsService.getItemsFromGitApi());

    }
    

}
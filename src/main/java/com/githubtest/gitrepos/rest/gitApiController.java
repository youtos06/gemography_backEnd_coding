package com.githubtest.gitrepos.rest;



import com.githubtest.gitrepos.consumingRest.ConsumingGitReposRest;
import com.githubtest.gitrepos.model.Items;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/gitTrends")
public class gitApiController {


    private final ConsumingGitReposRest consumingGitReposRest;

    public gitApiController(ConsumingGitReposRest consumingGitReposRest) {
        this.consumingGitReposRest = consumingGitReposRest;
    }

    @GetMapping()
    public ResponseEntity<Items> getTrendRepositoriesForLastMonth(){

        return ResponseEntity.ok().body(consumingGitReposRest.getMostFamousRepository());

    }


}
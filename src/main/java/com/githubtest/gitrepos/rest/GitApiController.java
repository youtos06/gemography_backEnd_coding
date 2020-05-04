package com.githubtest.gitrepos.rest;




import com.githubtest.gitrepos.model.Items;
import com.githubtest.gitrepos.model.dto.ItemsDto;
import com.githubtest.gitrepos.service.ItemsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/gitTrends")
public class GitApiController {


    private final ItemsService itemsService;

    public GitApiController(ItemsService itemsService) {
        this.itemsService = itemsService;
    }

    @GetMapping()
    public ResponseEntity<Items> getTrendRepositoriesForLastMonth(){

        return ResponseEntity.ok().body(itemsService.getRepositoriesFromGitApiLastMonth());

    }

    @GetMapping("/{language}")
    public ResponseEntity<ItemsDto> getTrendRepositoriesForLastMonthByLanguage(@PathVariable String language){
        if(language == null){
            language = "undefined";
        }
        language = language.toLowerCase();

        return ResponseEntity.ok().body(itemsService.getRepositoriesAccordingToLanguageFromLastMonth(language));

    }


}
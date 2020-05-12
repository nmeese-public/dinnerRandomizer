package com.dinnergenerator.dinnergenerator.controllers;

import com.dinnergenerator.dinnergenerator.commons.DinnerGeneratorResponse;
import com.dinnergenerator.dinnergenerator.services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class RecipeController {

    @Autowired
    RecipeService recipeService;

    @GetMapping(value = "/{mealType}", produces = "application/json")
        public ResponseEntity<DinnerGeneratorResponse> testService(@PathVariable String mealType){
        DinnerGeneratorResponse response = new DinnerGeneratorResponse();

        String correlationId = UUID.randomUUID().toString();
        HttpHeaders headers = new HttpHeaders();
        headers.add("correlationId",correlationId);
        response.setData(recipeService.getRecipeByKeyword(mealType));

        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(response);
    }

    @GetMapping(value = "/random/{mealType}",produces = "application/json")
    public ResponseEntity<DinnerGeneratorResponse> getRandomRecipe(@PathVariable String mealType){

        DinnerGeneratorResponse response = new DinnerGeneratorResponse();
        String correlationId = UUID.randomUUID().toString();
        HttpHeaders headers = new HttpHeaders();
        headers.add("correlationId", correlationId);
        response.setData(recipeService.getRandomRecipeByKeyword(mealType));
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(response);

    }
}

package com.dinnergenerator.dinnergenerator.controllers;

import com.dinnergenerator.dinnergenerator.commons.DinnerGeneratorResponse;
import com.dinnergenerator.dinnergenerator.services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.MalformedURLException;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class RecipeController {

    @Autowired
    RecipeService recipeService;

    @GetMapping(value = "/{mealType}", produces = "application/json")
        public ResponseEntity<DinnerGeneratorResponse> testService(@PathVariable String mealType,
                                                                   @RequestParam(required = false) Integer pageNum,
                                                                   @RequestParam(required = false) String ingredients){
        DinnerGeneratorResponse response = new DinnerGeneratorResponse();


        String correlationId = UUID.randomUUID().toString();
        HttpHeaders headers = new HttpHeaders();
        headers.add("correlationId",correlationId);
        try {

            response.setData(recipeService.getRecipeByKeyword(mealType, pageNum, ingredients));

            return ResponseEntity.status(HttpStatus.OK).headers(headers).body(response);
        }catch(IllegalStateException | MalformedURLException ex){
            response.setError(ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).headers(headers).body(response);
        }
    }


    @GetMapping(value = "/random/{mealType}",produces = "application/json")
    public ResponseEntity<DinnerGeneratorResponse> getRandomRecipe(@PathVariable String mealType,
                                                                   @RequestParam(required = false) String ingredients) throws MalformedURLException {

        DinnerGeneratorResponse response = new DinnerGeneratorResponse();
        String correlationId = UUID.randomUUID().toString();
        HttpHeaders headers = new HttpHeaders();
        headers.add("correlationId", correlationId);
        response.setData(recipeService.getRandomRecipeByKeyword(mealType,ingredients));
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(response);

    }
}

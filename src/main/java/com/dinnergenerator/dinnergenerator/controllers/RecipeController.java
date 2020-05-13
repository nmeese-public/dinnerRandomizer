package com.dinnergenerator.dinnergenerator.controllers;

import com.dinnergenerator.dinnergenerator.commons.DinnerGeneratorResponse;
import com.dinnergenerator.dinnergenerator.services.RecipeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private Logger logger = LoggerFactory.getLogger(RecipeController.class);

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
            if(response.getData().toString().equals("[]")){
                response.setError("No Data Found");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).headers(headers).body(response);
            }
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
        if(response.getData() == null){
            response.setError("No data to return");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).headers(headers).body(response);
        }

        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(response);

    }
}

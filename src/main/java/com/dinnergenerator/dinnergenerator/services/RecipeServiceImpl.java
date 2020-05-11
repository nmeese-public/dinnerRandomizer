package com.dinnergenerator.dinnergenerator.services;

import com.dinnergenerator.dinnergenerator.models.RecipePuppyModel;
import com.dinnergenerator.dinnergenerator.models.RecipePuppyResponse;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


@Service("RecipeService")
public class RecipeServiceImpl implements RecipeService {


    @Override
    public String getRecipeByKeyword(String mealType) {

        final String baseUrl = "http://www.recipepuppy.com/api/?q=";

        Gson gson = new Gson();

        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(baseUrl + mealType,String.class);
        RecipePuppyResponse response = gson.fromJson(result,RecipePuppyResponse.class);

        ArrayList<RecipePuppyModel> recipePuppyModels = response.getRecipePuppyModels();

        assert recipePuppyModels != null;
        return recipePuppyModels.toString();
    }
}

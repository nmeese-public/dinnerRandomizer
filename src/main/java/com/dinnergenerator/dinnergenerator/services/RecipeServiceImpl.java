package com.dinnergenerator.dinnergenerator.services;

import com.dinnergenerator.dinnergenerator.models.RecipePuppyModel;
import com.dinnergenerator.dinnergenerator.models.RecipePuppyResponse;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


@Service("RecipeService")
public class RecipeServiceImpl implements RecipeService {


    @Override
    public List<RecipePuppyModel> getRecipeByKeyword(String mealType) {

        final String baseUrl = "http://www.recipepuppy.com/api/?q=";

        Gson gson = new Gson();

        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(baseUrl + mealType,String.class);
        RecipePuppyResponse response = gson.fromJson(result,RecipePuppyResponse.class);

        ArrayList<RecipePuppyModel> recipePuppyModels = response.getRecipePuppyModels();

        assert recipePuppyModels != null;
        return recipePuppyModels;
    }

    @Override
    public RecipePuppyModel getRandomRecipeByKeyword(String mealType) {

        List<RecipePuppyModel> list = getRecipeByKeyword(mealType);
        assert list != null;
        Random rand = new Random();

        return list.get(rand.nextInt(list.size()));
    }
}

package com.dinnergenerator.dinnergenerator.services;

import com.dinnergenerator.dinnergenerator.models.RecipePuppyModel;

import java.net.MalformedURLException;
import java.util.List;

public interface RecipeService {

    List<RecipePuppyModel> getRecipeByKeyword(String mealType, Integer pageNum, String ingredients) throws MalformedURLException;

    RecipePuppyModel getRandomRecipeByKeyword(String mealType, String ingredients) throws MalformedURLException;

}

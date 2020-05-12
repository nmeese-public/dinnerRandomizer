package com.dinnergenerator.dinnergenerator.services;

import com.dinnergenerator.dinnergenerator.models.RecipePuppyModel;

import java.util.List;

public interface RecipeService {

    List<RecipePuppyModel> getRecipeByKeyword(String mealType);

    RecipePuppyModel getRandomRecipeByKeyword(String mealType);

}

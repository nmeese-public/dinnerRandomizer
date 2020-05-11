package com.dinnergenerator.dinnergenerator.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class RecipePuppyResponse {

    @SerializedName("results")
    private ArrayList<RecipePuppyModel> recipePuppyModels;

    public ArrayList<RecipePuppyModel> getRecipePuppyModels(){
        return recipePuppyModels;
    }

}

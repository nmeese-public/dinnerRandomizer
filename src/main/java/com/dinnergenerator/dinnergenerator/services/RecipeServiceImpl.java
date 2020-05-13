package com.dinnergenerator.dinnergenerator.services;

import com.dinnergenerator.dinnergenerator.models.RecipePuppyModel;
import com.dinnergenerator.dinnergenerator.models.RecipePuppyResponse;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.MalformedURLException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.sun.jndi.toolkit.url.UrlUtil.decode;


@Service("RecipeService")
public class RecipeServiceImpl implements RecipeService {

    private Logger logger =  LoggerFactory.getLogger(RecipeService.class);

    @Override
    public List<RecipePuppyModel> getRecipeByKeyword(String mealType, Integer pageNum, String ingredients ) throws MalformedURLException {

        if(pageNum == null){
            pageNum = 1;
        }
        if(ingredients == null){
            ingredients = "";
        }

        Gson gson = new Gson();

        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(getUrlString(mealType,pageNum,ingredients),String.class);
        RecipePuppyResponse response = gson.fromJson(result,RecipePuppyResponse.class);

        ArrayList<RecipePuppyModel> recipePuppyModels = response.getRecipePuppyModels();

        assert recipePuppyModels != null;
        return recipePuppyModels;
    }

    private URI getUrlString(String mealType, int pageNum, String ingredients) throws MalformedURLException {

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.newInstance()
                .scheme("http")
                .host("www.recipepuppy.com")
                .path("/api/")
                .query("q=" + decode(mealType))
                .path("/")
                .query("i=" + ingredients)
                .path("/")
                .query("p=" + pageNum);

        logger.info(uriBuilder.toUriString());

        return URI.create(uriBuilder.toUriString());
    }

    @Override
    public RecipePuppyModel getRandomRecipeByKeyword(String mealType, String ingredients) throws MalformedURLException {

        Random pageRand = new Random();
        Integer min = 1;
        Integer max = 10;

        List<RecipePuppyModel> list = getRecipeByKeyword(mealType, pageRand.nextInt((max - min) + 1) + min, ingredients);
        Random rand = new Random();

        logger.info(list.toString());

        if(list.size() > 0) {

            return list.get(rand.nextInt(list.size()));
        }
        return null;
    }
}

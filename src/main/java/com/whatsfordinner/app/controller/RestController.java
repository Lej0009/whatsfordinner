package com.whatsfordinner.app.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.whatsfordinner.app.dao.IngredientDao;
import com.whatsfordinner.app.models.Ingredient;
import com.whatsfordinner.app.models.Recipe;
import com.whatsfordinner.app.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    @Autowired
    private IngredientDao ingredientDao;

    public String ingredientString;
    public ClientHttpResponse response;
    public String jsonStr;
    public Recipe jsonObj;

    public Recipe getRecipeResults() throws IOException {

        User user = new User();
        Iterable<Ingredient> ingredients = ingredientDao.findAll();
        String searchStr = "";

        // iterate through ingredients, create search string to add to backend of api address
        for (Ingredient ingredient : ingredients) {
            ingredientString = ingredientString + ingredient.getIngredientName() + ",";
            String subStr = ingredientString.substring(0, ingredientString.length() - 1);
            searchStr = subStr.replaceAll("null", "");
        }

        RestTemplate restTemplate = new RestTemplate();

        restTemplate.getInterceptors().add((request, body, execution) -> {
            ClientHttpResponse response = execution.execute(request,body);
            response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
            return response;
        });

        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
        //Add the Jackson Message converter
        messageConverters.add(new MappingJackson2HttpMessageConverter());
        //Add the message converters to the restTemplate
        restTemplate.setMessageConverters(messageConverters);

        // call API
        final String url = ("http://www.recipepuppy.com/api/?i=" + searchStr);
        Recipe recipesResults = restTemplate.getForObject(url, Recipe.class);

            return recipesResults;
    }

}

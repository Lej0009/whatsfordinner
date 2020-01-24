package com.whatsfordinner.app.controller;

import com.whatsfordinner.app.dao.IngredientDao;
import com.whatsfordinner.app.models.Ingredient;
import com.whatsfordinner.app.models.Recipe;
import com.whatsfordinner.app.models.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/")
public class RecipeController {

    @Autowired
    private IngredientDao ingredientDao;

    public String ingredientString;

    @GetMapping("home/recipes")
    public String getRecipeResults(Model model) {

        Iterable<Ingredient> ingredients = ingredientDao.findAll();

        String repStr = "";

        for (Ingredient ingredient : ingredients) {
            ingredientString = ingredientString + ingredient.getIngredientName() + ",";
            String subStr = ingredientString.substring(0, ingredientString.length() - 1);
            repStr = subStr.replaceAll("null", "");
        }

//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.ALL);
//        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

        RestTemplate restTemplate = new RestTemplate();

        List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
        //Add the Jackson Message converter
        messageConverters.add(new MappingJackson2HttpMessageConverter());
        //Add the message converters to the restTemplate
        restTemplate.setMessageConverters(messageConverters);

        final String url = ("http://www.recipepuppy.com/api/?i="
                + repStr);
        Recipe recipes = restTemplate.getForObject(url, Recipe.class);



        if (recipes != null) {
            model.addAttribute("recipes", recipes);
            model.addAttribute("title", "Your Recipes");
            return "recipes";
        }
        return "error";
    }

}

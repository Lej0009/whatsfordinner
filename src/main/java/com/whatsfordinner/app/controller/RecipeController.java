package com.whatsfordinner.app.controller;

import com.whatsfordinner.app.dao.IngredientDao;
import com.whatsfordinner.app.models.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Controller
@RequestMapping("/")
public class RecipeController {

    @Autowired
    private IngredientDao ingredientDao;

    public List<Results> recipeResultsList;

    @GetMapping("/home/recipes")
    public List<Results> getRecipeResults(List<String> ingredients, Model model) {

        for (String ingredient : ingredients) {
            final String url = ("https://recipe-puppy.p.rapidapi.com/?i="
                    + ingredient);
            RestTemplate restTemplate = new RestTemplate();
            Results result = restTemplate.getForObject(url, Results.class);
            recipeResultsList.add(result);
        }
        model.addAttribute("recipes", recipeResultsList);
        model.addAttribute("title", "Your Recipes");

        return recipeResultsList;
    }
}

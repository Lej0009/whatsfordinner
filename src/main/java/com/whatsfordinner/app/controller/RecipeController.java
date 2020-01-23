package com.whatsfordinner.app.controller;

import com.whatsfordinner.app.Recipe;
import com.whatsfordinner.app.dao.IngredientDao;
import com.whatsfordinner.app.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Controller
public class RecipeController {

    @Autowired
    private IngredientDao ingredientDao;

    public List<Recipe> recipeList;

    public List<Recipe> getRecipes(List<String> ingredients) {

        for (String ingredient : ingredients) {
            final String url = ("https://api.spoonacular.com/recipes/findByIngredients?ingredients="
                    + ingredients);
            RestTemplate restTemplate = new RestTemplate();
            Recipe recipe = restTemplate.getForObject(url, Recipe.class);
            recipeList.add(recipe);
        }
        return recipeList;
    }

    @GetMapping("/home/recipes")
    public String displayRecipes(Model model, Recipe recipe, User user) {

        model.addAttribute("recipes", recipeList);
        model.addAttribute("title", "Your Recipes");

        return "recipes";
    }

}

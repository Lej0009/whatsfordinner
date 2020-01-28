package com.whatsfordinner.app.controller;

import com.whatsfordinner.app.models.Recipe;
import com.whatsfordinner.app.models.Results;
import org.hibernate.validator.constraints.pl.REGON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.ArrayList;

@Controller
@RequestMapping("/")
public class RecipeController {

    @Autowired
    private RestController restController;

    @RequestMapping("/home/recipes")
    public String displayRecipeResults(Model model) throws IOException {

        ArrayList<Results> resultsArrayList = restController.getRecipeResults().getResults();

        model.addAttribute("results", resultsArrayList);
        model.addAttribute("title", "Your Recipes");

        return "recipes";
    }
}

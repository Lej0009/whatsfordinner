package com.whatsfordinner.app.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.mail.imap.protocol.Item;
import com.whatsfordinner.app.dao.IngredientDao;
import com.whatsfordinner.app.models.Ingredient;
import com.whatsfordinner.app.models.Recipe;
import com.whatsfordinner.app.models.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.DataInput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/")
public class RecipeController {

    @Autowired
    private IngredientDao ingredientDao;

    public String ingredientString;
    public HttpEntity<String> entity;
    public ClientHttpResponse response;

    @GetMapping("home/recipes")
    public String getRecipeResults(Model model) throws IOException {

        Iterable<Ingredient> ingredients = ingredientDao.findAll();

        String repStr = "";

        for (Ingredient ingredient : ingredients) {
            ingredientString = ingredientString + ingredient.getIngredientName() + ",";
            String subStr = ingredientString.substring(0, ingredientString.length() - 1);
            repStr = subStr.replaceAll("null", "");
        }

//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
//        HttpEntity<?> entity = new HttpEntity<Object>(headers);

        RestTemplate restTemplate = new RestTemplate();

        restTemplate.getInterceptors().add((request, body, execution) -> {
            ClientHttpResponse response = execution.execute(request,body);
            response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
            return response;
        });

        List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
        //Add the Jackson Message converter
        messageConverters.add(new MappingJackson2HttpMessageConverter());
        //Add the message converters to the restTemplate
        restTemplate.setMessageConverters(messageConverters);

        final String url = ("http://www.recipepuppy.com/api/?i=" + repStr);
        ResponseEntity<Recipe> recipes = restTemplate.exchange(url, HttpMethod.GET, (HttpEntity<?>) response, Recipe.class);

        String stringRecipes = recipes.toString();
        String recipesFixed = stringRecipes.replaceAll("<", "");

        ObjectMapper mapper = new ObjectMapper();

//        mapper = new ObjectMapper().configure(com.fasterxml.jackson.core.JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        Recipe jsonObj = mapper.readValue(recipesFixed, Recipe.class);

        if (jsonObj != null) {
            model.addAttribute("recipes", jsonObj);
            model.addAttribute("title", "Your Recipes");
            return "recipes";
        }
        return "error";
    }

}

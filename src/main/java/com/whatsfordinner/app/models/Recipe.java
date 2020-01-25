package com.whatsfordinner.app.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Recipe {

    private String title;
    private String href;
    private String ingredients;
    private ArrayList<Results> results;

    public Recipe() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    //    public Array getResults() {
//        return results;
//    }
//
//    public void setResults(Array results) {
//        this.results = results;
//    }

    public void setResults(ArrayList<Results> results) {
        this.results = results;
    }


    @Override
    public String toString() {
        return "Recipe{" +
                "title=" + title +
                ", href=" + href +
                ", ingredients=" + ingredients +
                ", results=" + results +
                "}";
    }

}

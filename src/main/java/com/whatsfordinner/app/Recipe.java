package com.whatsfordinner.app;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.lang.reflect.Array;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Recipe {

    private Array missedIngredients;
    private String title;
    private Array unusedIngredients;
    private Array usedIngredients;

    public Recipe() {
    }

    public Array getMissedIngredients() {
        return missedIngredients;
    }

    public void setMissedIngredients(Array missedIngredients) {
        this.missedIngredients = missedIngredients;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Array getUnusedIngredients() {
        return unusedIngredients;
    }

    public void setUnusedIngredients(Array unusedIngredients) {
        this.unusedIngredients = unusedIngredients;
    }

    public Array getUsedIngredients() {
        return usedIngredients;
    }

    public void setUsedIngredients(Array usedIngredients) {
        this.usedIngredients = usedIngredients;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "missedIngredients='" + missedIngredients + '\'' +
                ", title=" + title +
                ", unusedIngredients=" + unusedIngredients +
                ", usedIngredients=" + usedIngredients +
                '}';
    }

}

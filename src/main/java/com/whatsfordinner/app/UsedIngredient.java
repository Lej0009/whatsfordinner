package com.whatsfordinner.app;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UsedIngredient {

    private String original;

    public UsedIngredient() {
    }

    public String getOriginal() {
        return original;
    }

    public void setOriginal(String original) {
        this.original = original;
    }

    @Override
    public String toString() {
        return "usedIngredients{" +
                "original=" + original + '\'' +
                '}';
    }

}

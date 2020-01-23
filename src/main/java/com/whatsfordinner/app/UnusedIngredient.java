package com.whatsfordinner.app;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UnusedIngredient {

    private String original;

    public UnusedIngredient() {
    }

    public String getOriginal() {
        return original;
    }

    public void setOriginal(String original) {
        this.original = original;
    }

    @Override
    public String toString() {
        return "unUsedIngredients{" +
                "original=" + original + '\'' +
                '}';
    }
}

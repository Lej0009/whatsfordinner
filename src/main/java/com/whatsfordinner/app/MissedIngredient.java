package com.whatsfordinner.app;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MissedIngredient {

    private String original;

    public MissedIngredient() {
    }

    public String getOriginal() {
        return original;
    }

    public void setOriginal(String original) {
        this.original = original;
    }

    @Override
    public String toString() {
        return "missedIngredients{" +
                "original=" + original + '\'' +
                '}';
    }
}

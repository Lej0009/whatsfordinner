package com.whatsfordinner.app.formbeans;

import javax.persistence.Column;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;

public class AddIngredientForm {

    @NotBlank
    @Column(name = "ingredient_name")
    @Max(30)
    public String ingredientName;

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }
}

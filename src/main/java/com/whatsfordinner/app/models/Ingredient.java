package com.whatsfordinner.app.models;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
public class Ingredient {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "ingredient_id")
    private Integer ingredientId;

    @NotBlank
    @Column(name = "ingredient_name")
    @Max(30)
    private String ingredientName;

    //    @ManyToOne
    @Column(name = "user_id")
    private Integer userId;

    public Ingredient() { }

    public Ingredient(@NotBlank @Max(30) String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int user) {
        this.userId = userId;
    }
}

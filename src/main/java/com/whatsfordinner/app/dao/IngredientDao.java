package com.whatsfordinner.app.dao;

import com.whatsfordinner.app.models.Ingredient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Repository
@Transactional
public interface IngredientDao extends CrudRepository<Ingredient, Integer> {

    Iterable<Ingredient> findAllByUserId(Integer userId);
}

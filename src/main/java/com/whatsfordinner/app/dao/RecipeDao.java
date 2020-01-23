package com.whatsfordinner.app.dao;

import com.whatsfordinner.app.models.Recipe;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface RecipeDao extends CrudRepository<Recipe, Integer> {

}

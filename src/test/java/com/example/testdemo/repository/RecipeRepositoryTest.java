package com.example.testdemo.repository;

import com.example.testdemo.model.Recipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class RecipeRepositoryTest {

    @Autowired
    private RecipeRepository recipeRepository;

    @BeforeEach
    void setUp() {
        recipeRepository.deleteAll();
        recipeRepository.flush();

        Recipe recipe = Recipe.builder().name("A long name here").build();
        recipeRepository.saveAndFlush(recipe);

        Recipe recipe1 = Recipe.builder().name("A short one").build();
        recipeRepository.saveAndFlush(recipe1);
    }

    @Test
    void searchRecipes() {
        List<Recipe> results = recipeRepository.findAll();
        assertEquals(2, results.size());
        results = recipeRepository.searchRecipes("A long");
        assertEquals(1, results.size());
        assertEquals("A long name here", results.get(0).getName());
    }
}
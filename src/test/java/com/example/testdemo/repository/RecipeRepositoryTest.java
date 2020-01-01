package com.example.testdemo.repository;

import com.example.testdemo.model.Recipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static com.example.testdemo.model.RecipeAssert.*;

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
        assertThat(results).hasSize(2);
        results = recipeRepository.searchRecipes("A long");
        assertThat(results).hasSize(1);
        assertThat(results).satisfies(r -> {
           assertThat(r).hasSize(1);
           assertThat(r.get(0)).hasName("A long name here");
        });
    }
}
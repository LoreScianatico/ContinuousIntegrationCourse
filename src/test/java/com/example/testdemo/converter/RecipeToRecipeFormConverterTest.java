package com.example.testdemo.converter;

import com.example.testdemo.form.RecipeForm;
import com.example.testdemo.model.Recipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RecipeToRecipeFormConverterTest {

    private RecipeToRecipeFormConverter converter;

    @BeforeEach
    void setUp() {
        converter = new RecipeToRecipeFormConverter();
    }

    @Test
    void convert() {
        Recipe recipe = Recipe.builder().id(1L).name("Name").build();
        RecipeForm recipeForm = converter.convert(recipe);
        assertNotNull(recipeForm);
        assertEquals(recipe.getId(), recipeForm.getId());
        assertEquals(recipe.getName(), recipeForm.getName());
    }
}
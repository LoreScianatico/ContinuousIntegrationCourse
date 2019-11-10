package com.example.testdemo.converter;

import com.example.testdemo.form.RecipeForm;
import com.example.testdemo.model.Recipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RecipeFormToRecipeConverterTest {

    private RecipeFormToRecipeConverter recipeFormToRecipeConverter;

    @BeforeEach
    void setUp() {
        recipeFormToRecipeConverter = new RecipeFormToRecipeConverter();
    }

    @Test
    void convert() {
        RecipeForm recipeForm = RecipeForm.builder().id(1L).name("Name").build();
        Recipe recipe = recipeFormToRecipeConverter.convert(recipeForm);
        assertNotNull(recipe);
        assertEquals(recipe.getId(), recipeForm.getId());
        assertEquals(recipe.getName(), recipeForm.getName());
    }
}
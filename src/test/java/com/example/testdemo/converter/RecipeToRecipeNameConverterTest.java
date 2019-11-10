package com.example.testdemo.converter;

import com.example.testdemo.form.RecipeName;
import com.example.testdemo.model.Recipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RecipeToRecipeNameConverterTest {

    private RecipeToRecipeNameConverter converter;

    @BeforeEach
    void setUp() {
        converter = new RecipeToRecipeNameConverter();
    }

    @Test
    void convert() {

        Recipe recipe = Recipe.builder().id(1L).name("Name").build();
        RecipeName recipeName = converter.convert(recipe);
        assertNotNull(recipeName);
        assertEquals(recipe.getId(), recipeName.getId());
        assertEquals(recipe.getName(), recipeName.getName());

    }
}
package com.example.testdemo.converter;

import com.example.testdemo.form.RecipeForm;
import com.example.testdemo.model.Recipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.example.testdemo.form.RecipeFormAssert.assertThat;

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
        assertThat(recipeForm).satisfies(rF -> {
            assertThat(rF).isNotNull();
            assertThat(rF).hasId(recipe.getId());
            assertThat(rF).hasName(recipe.getName());
        });
    }
}
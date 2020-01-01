package com.example.testdemo.converter;

import com.example.testdemo.form.RecipeForm;
import com.example.testdemo.model.Recipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.example.testdemo.model.RecipeAssert.assertThat;


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
        assertThat(recipe).satisfies(r -> {
            assertThat(r).isNotNull();
            assertThat(r).hasId(recipeForm.getId());
            assertThat(r).hasName(recipeForm.getName());
        });
    }
}
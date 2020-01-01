package com.example.testdemo.converter;

import com.example.testdemo.form.RecipeName;
import com.example.testdemo.model.Recipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.example.testdemo.form.RecipeNameAssert.assertThat;

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
        assertThat(recipeName).satisfies(rN -> {
            assertThat(rN).isNotNull();
            assertThat(rN).hasId(recipe.getId());
            assertThat(rN).hasName(recipe.getName());
        });

    }
}
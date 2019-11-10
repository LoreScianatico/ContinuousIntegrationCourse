package com.example.testdemo.converter;

import com.example.testdemo.form.RecipeForm;
import com.example.testdemo.model.Recipe;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class RecipeFormToRecipeConverter implements Converter<RecipeForm, Recipe> {
    @Override
    public Recipe convert(RecipeForm recipeForm) {

        logger.debug("Converting recipe form to entity: {}", recipeForm.toString());
        return Recipe.builder()
                .id(recipeForm.getId())
                .name(recipeForm.getName())
                .cookTime(recipeForm.getCookTime())
                .prepTime(recipeForm.getPrepTime())
                .servings(recipeForm.getServings())
                .directions(recipeForm.getDirections())
                .build();
    }
}

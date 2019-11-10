package com.example.testdemo.converter;

import com.example.testdemo.form.IngredientForm;
import com.example.testdemo.form.RecipeForm;
import com.example.testdemo.model.Ingredient;
import com.example.testdemo.model.Recipe;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@Slf4j
public class RecipeToRecipeFormConverter implements Converter<Recipe, RecipeForm> {
    @Override
    public RecipeForm convert(Recipe recipe) {

        logger.debug("Converting recipe entity to form: {}", recipe.toString());
        return RecipeForm.builder()
                .id(recipe.getId())
                .name(recipe.getName())
                .cookTime(recipe.getCookTime())
                .prepTime(recipe.getPrepTime())
                .servings(recipe.getServings())
                .directions(recipe.getDirections())
                .ingredients(convert(recipe.getIngredients(), recipe.getId()))
                .build();
    }

    private List<IngredientForm> convert(Set<Ingredient> ingredients, Long recipeId) {
        return ingredients.stream()
                .map(ingredient -> IngredientForm.builder()
                        .recipeId(recipeId)
                        .amount(ingredient.getAmount())
                        .name(ingredient.getName())
                        .build()
                ).collect(Collectors.toList());

    }
}

package com.example.testdemo.service;

import com.example.testdemo.form.IngredientForm;
import com.example.testdemo.form.RecipeForm;
import com.example.testdemo.form.RecipeName;

import java.util.List;

public interface RecipeService {
    List<RecipeName> getAllRecipes();

    RecipeForm getRecipe(Long id);

    void saveRecipe(RecipeForm recipeForm);

    void deleteRecipe(Long id);

    void saveIngredient(IngredientForm ingredientForm);
}

package com.example.testdemo.service;

import com.example.testdemo.exception.NotFoundException;
import com.example.testdemo.form.IngredientForm;
import com.example.testdemo.form.RecipeForm;
import com.example.testdemo.form.RecipeName;
import com.example.testdemo.model.Ingredient;
import com.example.testdemo.model.Recipe;
import com.example.testdemo.repository.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class RecipeServiceImpl implements RecipeService {

    private ConversionService conversionService;

    private RecipeRepository recipeRepository;

    public RecipeServiceImpl(ConversionService conversionService, RecipeRepository recipeRepository) {
        this.conversionService = conversionService;
        this.recipeRepository = recipeRepository;
    }

    @Override
    public List<RecipeName> getAllRecipes() {
        logger.debug("Getting all recipes...");
        return recipeRepository.findAll()
                .stream()
                .map(r -> conversionService.convert(r, RecipeName.class))
                .collect(Collectors.toList());
    }

    @Override
    public RecipeForm getRecipe(Long id) {
        logger.debug("Getting recipe with id: {}", id);
        Recipe recipe = recipeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Recipe not found: " + id));
        return conversionService.convert(recipe, RecipeForm.class);
    }

    @Override
    public void saveRecipe(RecipeForm recipeForm) {
        logger.debug("Saving recipe...");
        Recipe previous = getPrevious(recipeForm);
        Recipe recipe = conversionService.convert(recipeForm, Recipe.class);
        recipe.setIngredients(previous.getIngredients());
        recipeRepository.save(recipe);
    }

    private Recipe getPrevious(RecipeForm recipeForm) {
        if (recipeForm.getId() == null){
            return Recipe.builder().build();
        }
        return recipeRepository.findById(recipeForm.getId())
                .orElseThrow(() -> new NotFoundException("Recipe not found: " + recipeForm.getId()));
    }

    @Override
    public void deleteRecipe(Long id) {
        logger.debug("Deleting recipe...");
        recipeRepository.deleteById(id);
    }

    @Override
    public void saveIngredient(IngredientForm ingredientForm) {
        logger.debug("Adding ingredient to recipe...");
        Ingredient ingredient = conversionService.convert(ingredientForm, Ingredient.class);
        Recipe recipe = recipeRepository.findById(ingredientForm.getRecipeId())
                .orElseThrow(() -> new NotFoundException("Recipe not found: " + ingredientForm.getRecipeId()));
        recipe.addIngredient(ingredient);
        recipeRepository.save(recipe);
    }
}

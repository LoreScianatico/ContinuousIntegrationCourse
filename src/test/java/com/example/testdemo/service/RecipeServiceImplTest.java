package com.example.testdemo.service;

import com.example.testdemo.exception.NotFoundException;
import com.example.testdemo.form.RecipeForm;
import com.example.testdemo.form.RecipeName;
import com.example.testdemo.model.Recipe;
import com.example.testdemo.repository.RecipeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.convert.ConversionService;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;
import static com.example.testdemo.form.RecipeNameAssert.*;
import static com.example.testdemo.form.RecipeFormAssert.*;

@ExtendWith(MockitoExtension.class)
class RecipeServiceImplTest {

    @Mock
    private RecipeRepository recipeRepository;

    @Mock
    private ConversionService conversionService;

    @InjectMocks
    private RecipeServiceImpl recipeService;

    @Test
    void getAllRecipes() {
        RecipeName recipeName = RecipeName.builder().id(1L).name("Just a description").build();
        Recipe recipe = Recipe.builder().id(1L).name("Just a description").build();
        Mockito.when(conversionService.convert(ArgumentMatchers.any(Recipe.class), ArgumentMatchers.eq(RecipeName.class))).thenReturn(recipeName);
        Mockito.when(recipeRepository.findAll()).thenReturn(List.of(recipe));
        List<RecipeName> results = recipeService.getAllRecipes();
        assertThat(results).satisfies(r -> {
            assertThat(r).isNotNull();
            assertThat(r.get(0)).hasId(1L);
            assertThat(r.get(0)).hasName("Just a description");
        });
    }

    @Test
    void getRecipe() {
        Recipe recipe = Recipe.builder().id(1L).name("Just a description").build();
        RecipeForm recipeFormMock = RecipeForm.builder().build();
        Mockito.when(recipeRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(recipe));
        Mockito.when(conversionService.convert(ArgumentMatchers.any(Recipe.class), ArgumentMatchers.eq(RecipeForm.class))).thenReturn(recipeFormMock);
        RecipeForm recipeForm = recipeService.getRecipe(1L);
        assertThat(recipeForm).isNotNull();
    }

    @Test
    void getRecipeNotFound() {
        Mockito.when(recipeRepository.findById(ArgumentMatchers.eq(2L))).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> recipeService.getRecipe(2L));
    }

    @Test
    void saveRecipe() {
        RecipeForm recipeForm = RecipeForm.builder().build();
        Recipe recipe = Recipe.builder().build();
        Mockito.when(conversionService.convert(ArgumentMatchers.any(RecipeForm.class), ArgumentMatchers.eq(Recipe.class))).thenReturn(recipe);
        recipeService.saveRecipe(recipeForm);
    }

    @Test
    void deleteRecipe() {
        recipeService.deleteRecipe(1L);
    }
}
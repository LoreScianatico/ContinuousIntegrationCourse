package com.example.testdemo.controller;

import com.example.testdemo.form.RecipeForm;
import com.example.testdemo.form.RecipeName;
import com.example.testdemo.service.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

@WebMvcTest({RecipeController.class})
class RecipeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RecipeService recipeService;

    @BeforeEach
    void setUp() {
        RecipeName recipe = RecipeName.builder().id(1L).name("Just a description").build();
        RecipeForm recipeForm = RecipeForm.builder().build();
        Mockito.when(recipeService.getAllRecipes()).thenReturn(List.of(recipe));
        Mockito.when(recipeService.getRecipe(ArgumentMatchers.anyLong())).thenReturn(recipeForm);
    }

    @Test
    void getRecipeList() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("recipes"));

        Mockito.verify(recipeService, Mockito.only()).getAllRecipes();
    }

    @Test
    void getRecipe() throws Exception{
        this.mockMvc.perform(MockMvcRequestBuilders.get("/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("recipe"));
        Mockito.verify(recipeService, Mockito.only()).getRecipe(ArgumentMatchers.anyLong());
    }

    @Test
    void saveRecipe() throws Exception{
        this.mockMvc.perform(MockMvcRequestBuilders.post("/", new RecipeForm()))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection());
        Mockito.verify(recipeService, Mockito.only()).saveRecipe(ArgumentMatchers.any(RecipeForm.class));
    }

    @Test
    void deleteRecipe() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/delete/1"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection());
        Mockito.verify(recipeService, Mockito.only()).deleteRecipe(ArgumentMatchers.anyLong());
    }

}
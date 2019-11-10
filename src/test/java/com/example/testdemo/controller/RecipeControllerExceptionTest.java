package com.example.testdemo.controller;

import com.example.testdemo.exception.ExceptionAdvice;
import com.example.testdemo.exception.NotFoundException;
import com.example.testdemo.service.RecipeService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest({RecipeController.class})
@ContextConfiguration(classes = {ExceptionAdvice.class})
class RecipeControllerExceptionTest {

    @MockBean
    private RecipeService recipeService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testNotFound() throws Exception{
        Mockito.when(recipeService.getRecipe(ArgumentMatchers.anyLong())).thenThrow(new NotFoundException("Not found"));

        this.mockMvc.perform(MockMvcRequestBuilders.get("/1"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());

        Mockito.verify(recipeService, Mockito.never()).getRecipe(ArgumentMatchers.anyLong());

    }

}

package com.example.testdemo.controller;

import com.example.testdemo.form.IngredientForm;
import com.example.testdemo.form.RecipeForm;
import com.example.testdemo.service.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
public class RecipeController {

    private RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/index")
    public String index(){
        return "redirect:/";
    }

    @GetMapping({"","/"})
    public String getRecipeList(Model model){

        logger.info("Getting all recipes...");

        model.addAttribute("recipes", recipeService.getAllRecipes());

        return "index";
    }

    @GetMapping("/{id}")
    public String getRecipe(@PathVariable Long id, Model model){

        logger.info("Getting recipe: {}", id);

        model.addAttribute("recipe", recipeService.getRecipe(id));

        return "recipe";
    }

    @GetMapping("/new")
    public String getRecipeEmptyForm(Model model){

        logger.info("Creating empty form...");

        model.addAttribute("recipe", new RecipeForm());

        return "recipe";
    }


    @PostMapping({"","/"})
    public String saveRecipe(@ModelAttribute RecipeForm recipeForm){

        logger.info("Saving recipe...");

        recipeService.saveRecipe(recipeForm);

        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteRecipe(@PathVariable Long id){

        logger.info("Removing recipe with id: {}", id);

        recipeService.deleteRecipe(id);

        return "redirect:/";
    }

    @GetMapping("/{id}/ingredient/new")
    public String addIngredient(@PathVariable Long id, Model model){

        logger.info("Creating empty form...");

        model.addAttribute("ingredient", IngredientForm.builder().recipeId(id).build());

        return "ingredient";
    }

    @PostMapping({"/{id}"})
    public String saveIngredient(@PathVariable Long id, @ModelAttribute IngredientForm ingredientForm){

        logger.info("Saving ingredient...");

        recipeService.saveIngredient(ingredientForm);

        return "redirect:/"+id;
    }

}

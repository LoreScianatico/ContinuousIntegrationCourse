package com.example.testdemo.converter;

import com.example.testdemo.form.RecipeName;
import com.example.testdemo.model.Recipe;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class RecipeToRecipeNameConverter implements Converter<Recipe, RecipeName> {

    @Override
    public RecipeName convert(Recipe recipe) {

        logger.debug("Converting recipe entity to names list: {}", recipe.toString());
        return RecipeName.builder()
                .id(recipe.getId())
                .name(recipe.getName())
                .build();
    }

}

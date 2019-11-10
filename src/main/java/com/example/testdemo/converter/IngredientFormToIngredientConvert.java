package com.example.testdemo.converter;

import com.example.testdemo.form.IngredientForm;
import com.example.testdemo.model.Ingredient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class IngredientFormToIngredientConvert implements Converter<IngredientForm, Ingredient> {
    @Override
    public Ingredient convert(IngredientForm ingredientForm) {
        logger.debug("Converting ingredient form to ingredient {}", ingredientForm.toString());
        return Ingredient.builder()
                .amount(ingredientForm.getAmount())
                .name(ingredientForm.getName())
                .build();
    }
}

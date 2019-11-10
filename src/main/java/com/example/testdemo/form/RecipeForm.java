package com.example.testdemo.form;

import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RecipeForm {

    private Long id;

    private String name;

    private String prepTime;

    private String cookTime;

    private String servings;

    private String directions;

    @Singular
    private List<IngredientForm> ingredients;

}

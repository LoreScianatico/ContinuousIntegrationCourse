package com.example.testdemo.form;

import lombok.*;

@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class IngredientForm {
    private Long recipeId;

    private String name;

    private Integer amount;
}

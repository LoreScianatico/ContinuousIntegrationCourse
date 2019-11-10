package com.example.testdemo.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Builder
@Entity
@EqualsAndHashCode(of = "id")
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Recipe {

    @Id
    @GeneratedValue(generator = "SEC_RECIPE", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "SEC_RECIPE", sequenceName = "SEC_RECIPE", initialValue = 1, allocationSize=1)
    private Long id;

    private String name;

    @Column(length = 100, name = "prep_time")
    private String prepTime;

    @Column(length = 100, name = "cook_time")
    private String cookTime;

    @Column(length = 100, name = "servings")
    private String servings;

    @Lob
    private String directions;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "recipe_id")
    @Singular
    private Set<Ingredient> ingredients = new HashSet<>();

    public void addIngredient(Ingredient ingredient){
        ingredients.add(ingredient);
    }
}

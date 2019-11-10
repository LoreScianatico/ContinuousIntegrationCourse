package com.example.testdemo.repository;

import com.example.testdemo.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {

    @Query("select r from Recipe r where r.name like :searchedName%")
    List<Recipe> searchRecipes(String searchedName);

}

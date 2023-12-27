package ru.nsu.recipes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nsu.recipes.entity.Recipe;

import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {

    List<Recipe> findByNameContainsIgnoreCase(String name);

}

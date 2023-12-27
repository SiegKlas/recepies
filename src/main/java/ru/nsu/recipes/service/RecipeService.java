package ru.nsu.recipes.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nsu.recipes.entity.Recipe;
import ru.nsu.recipes.repository.RecipeRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
// TODO: add logic to exclude undesirable products
public class RecipeService {

    private final RecipeRepository recipeRepository;

    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }

    public List<Recipe> searchRecipesByName(String name) {
        return recipeRepository.findByNameStartingWithIgnoreCase(name);
    }

    public Recipe getRecipeById(Long id) {
        return recipeRepository.findById(id).get();
    }

}

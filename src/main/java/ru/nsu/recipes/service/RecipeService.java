package ru.nsu.recipes.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nsu.recipes.dto.CreateRecipeRequestDTO;
import ru.nsu.recipes.entity.Product;
import ru.nsu.recipes.entity.Recipe;
import ru.nsu.recipes.repository.ProductRepository;
import ru.nsu.recipes.repository.RecipeRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
// TODO: add logic to exclude undesirable products
public class RecipeService {

    private final RecipeRepository recipeRepository;
    private final ProductRepository productRepository;

    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }

    public List<Recipe> searchRecipesByName(String name) {
        return recipeRepository.findByNameContainsIgnoreCase(name);
    }

    public Recipe getRecipeById(Long id) {
        return recipeRepository.findById(id).get();
    }

    public void saveRecipe(CreateRecipeRequestDTO createRecipeRequestDTO) {
        Recipe newRecipe = new Recipe();
        newRecipe.setName(createRecipeRequestDTO.getRecipeName());
        newRecipe.setDescription(createRecipeRequestDTO.getRecipeDescription());

        List<Product> selectedIngredients = productRepository.findAllByIdIn(createRecipeRequestDTO.getProductIds());
        newRecipe.setProducts(selectedIngredients);

        recipeRepository.save(newRecipe);
    }

}

package ru.nsu.recipes.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nsu.recipes.dto.CreateRecipeRequestDTO;
import ru.nsu.recipes.entity.Product;
import ru.nsu.recipes.entity.Recipe;
import ru.nsu.recipes.entity.RecipesUser;
import ru.nsu.recipes.repository.ProductRepository;
import ru.nsu.recipes.repository.RecipeRepository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
// TODO: add logic to exclude undesirable products
public class RecipeService {

    private final RecipeRepository recipeRepository;
    private final ProductRepository productRepository;

    public List<Recipe> getAllRecipesWithRegardsToUserDesires() {
        RecipesUser recipesUser = UserUtilsService.getCurrentUser();

        // все это нужно делать на стороне бд, но как-то в падлу..
        return recipeRepository.findAll().stream()
                .filter(it -> it.getProducts().stream().noneMatch(product ->
                        recipesUser.getUndesirableProducts().contains(product)
                ))
                .sorted(Comparator.comparing(recipe ->
                        recipesUser.getFavouriteProducts()
                                .stream()
                                .filter(favouriteProduct -> recipe.getProducts().contains(favouriteProduct)).count()))
                .collect(Collectors.toList());
    }

    public List<Recipe> searchRecipesByName(String name) {
        return recipeRepository.findByNameContainsIgnoreCase(name);
    }

    public Recipe getRecipeById(Long id) {
        return recipeRepository.findById(id).get();
    }

    public void saveRecipe(CreateRecipeRequestDTO createRecipeRequestDTO) {
        Recipe newRecipe = new Recipe();
        newRecipe.setName(createRecipeRequestDTO.getName());
        newRecipe.setDescription(createRecipeRequestDTO.getDescription());

        List<Product> selectedIngredients = productRepository.findAllByIdIn(createRecipeRequestDTO.getIngredientIds());
        newRecipe.setProducts(selectedIngredients);

        recipeRepository.save(newRecipe);
    }

}

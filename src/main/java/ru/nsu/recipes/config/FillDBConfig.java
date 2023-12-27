package ru.nsu.recipes.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import ru.nsu.recipes.entity.Product;
import ru.nsu.recipes.entity.Recipe;
import ru.nsu.recipes.repository.ProductRepository;
import ru.nsu.recipes.repository.RecipeRepository;

import java.util.List;

@Configuration
public class FillDBConfig {

    private final ProductRepository productRepository;
    private final RecipeRepository recipeRepository;

    @Autowired
    public FillDBConfig(ProductRepository productRepository, RecipeRepository recipeRepository) {
        this.productRepository = productRepository;
        this.recipeRepository = recipeRepository;

        Product product1 = new Product();
        product1.setName("Яйца");

        Product product2 = new Product();
        product2.setName("Соль");

        Recipe recipe = new Recipe();
        recipe.setName("Жаренные яйца");
        recipe.setDescription("очень длинное описаниеочень длинное описаниеочень длинное описаниеочень длинное описаниеочень длинное описаниеочень длинное описаниеочень длинное описаниеочень длинное описаниеочень длинное описаниеочень длинное описаниеочень длинное описаниеочень длинное описаниеочень длинное описаниеочень длинное описаниеочень длинное описаниеочень длинное описаниеочень длинное описаниеочень длинное описание");
        recipe.getProducts().addAll(List.of(product1, product2));

        productRepository.save(product1);
        productRepository.save(product2);
        recipeRepository.save(recipe);
    }
}

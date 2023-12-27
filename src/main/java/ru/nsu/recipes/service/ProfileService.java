package ru.nsu.recipes.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nsu.recipes.entity.Product;
import ru.nsu.recipes.entity.RecipesUser;
import ru.nsu.recipes.repository.ProductRepository;
import ru.nsu.recipes.repository.RecipesUserRepository;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final ProductRepository productRepository;
    private final RecipesUserRepository userRepository;

    public void addFavouriteProduct(Long productId) {
        RecipesUser user = UserUtilsService.getCurrentUser();

        Product productToAdd = productRepository.findById(productId).get();
        user.getFavouriteProducts().add(productToAdd);

        userRepository.save(user);
    }

    public void addUndesirableProduct(Long productId) {
        RecipesUser user = UserUtilsService.getCurrentUser();

        Product productToAdd = productRepository.findById(productId).get();
        user.getUndesirableProducts().add(productToAdd);

        userRepository.save(user);
    }

    public void removeFavouriteProduct(Long productId) {

    }

    public void removeUndesirableProduct(Long productId) {

    }

}

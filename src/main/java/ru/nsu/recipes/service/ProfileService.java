package ru.nsu.recipes.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
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

    public void addFavouriteProduct(Long productId, Authentication authentication) {
        RecipesUser recipesUser =
                userRepository.findByUsername(UserUtilsService.getUsernameFromAuthentication(authentication));

        Product productToAdd = productRepository.findById(productId).get();
        recipesUser.getFavouriteProducts().add(productToAdd);

        userRepository.save(recipesUser);
    }

    public void addUndesirableProduct(Long productId, Authentication authentication) {
        RecipesUser recipesUser =
                userRepository.findByUsername(UserUtilsService.getUsernameFromAuthentication(authentication));

        Product productToAdd = productRepository.findById(productId).get();
        recipesUser.getUndesirableProducts().add(productToAdd);

        userRepository.save(recipesUser);
    }

    public void removeFavouriteProduct(Long productId) {

    }

    public void removeUndesirableProduct(Long productId) {

    }

}

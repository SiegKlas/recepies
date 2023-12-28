package ru.nsu.recipes.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.nsu.recipes.entity.RecipesUser;
import ru.nsu.recipes.repository.RecipesUserRepository;
import ru.nsu.recipes.service.ProductService;
import ru.nsu.recipes.service.ProfileService;
import ru.nsu.recipes.service.UserUtilsService;

@Controller
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;
    private final ProductService productService;
    private final RecipesUserRepository userRepository;

    @GetMapping("/profile")
    public String userProfile(Model model, Authentication authentication) {
        RecipesUser recipesUser =
                userRepository.findByUsername(UserUtilsService.getUsernameFromAuthentication(authentication));
        if (recipesUser == null) {
            // а мы можем сюда попасть?
            return "redirect:/login";
        }

        model.addAttribute("user", recipesUser);
        model.addAttribute("allProducts", productService.getAllProducts());

        return "profile";
    }

    @PostMapping("/profile/addFavorite")
    public String addFavoriteProduct(@RequestParam Long productId, Authentication authentication) {
        profileService.addFavouriteProduct(productId, authentication);

        return "redirect:/profile";
    }

    @PostMapping("/profile/addUndesirable")
    public String addUndesirableProduct(@RequestParam Long productId, Authentication authentication) {
        profileService.addUndesirableProduct(productId, authentication);

        return "redirect:/profile";
    }

}

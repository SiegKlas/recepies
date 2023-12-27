package ru.nsu.recipes.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.nsu.recipes.entity.RecipesUser;
import ru.nsu.recipes.service.ProductService;
import ru.nsu.recipes.service.ProfileService;
import ru.nsu.recipes.service.UserUtilsService;

@Controller
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;
    private final ProductService productService;

    @GetMapping("/profile")
    public String userProfile(Model model) {
        RecipesUser recipesUser = UserUtilsService.getCurrentUser();
        if (recipesUser == null) {
            // а мы можем сюда попасть?
            return "redirect:/login";
        }

        model.addAttribute("user", recipesUser);
        model.addAttribute("allProducts", productService.getAllProducts());

        return "profile";
    }

    @PostMapping("/profile/addFavorite")
    public String addFavoriteProduct(@RequestParam Long productId) {
        profileService.addFavouriteProduct(productId);

        return "redirect:/profile";
    }

    @PostMapping("/profile/addUndesirable")
    public String addUndesirableProduct(@RequestParam Long productId) {
        profileService.addUndesirableProduct(productId);

        return "redirect:/profile";
    }

}

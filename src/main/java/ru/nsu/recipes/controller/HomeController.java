package ru.nsu.recipes.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.nsu.recipes.entity.Recipe;
import ru.nsu.recipes.service.RecipeService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final RecipeService recipeService;

    @GetMapping
    public String showRecipes(Model model, Authentication authentication) {
        List<Recipe> recipes = recipeService.getAllRecipesWithRegardsToUserDesires(authentication);
        model.addAttribute("recipes", recipes);
        return "home";
    }

    @GetMapping("/search")
    public String searchRecipes(@RequestParam(name = "recipeName") String recipeName, Model model) {
        List<Recipe> recipes = recipeService.searchRecipesByName(recipeName);
        model.addAttribute("recipes", recipes);
        return "home";
    }

}

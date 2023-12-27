package ru.nsu.recipes.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.nsu.recipes.dto.CreateRecipeRequestDTO;
import ru.nsu.recipes.entity.Feedback;
import ru.nsu.recipes.entity.Product;
import ru.nsu.recipes.entity.Recipe;
import ru.nsu.recipes.service.FeedbackService;
import ru.nsu.recipes.service.ProductService;
import ru.nsu.recipes.service.RecipeService;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/recipe")
@RequiredArgsConstructor
public class RecipeController {

    private final RecipeService recipeService;
    private final ProductService productService;
    private final FeedbackService feedbackService;

    @GetMapping("/{id}")
    public String showRecipeDetails(@PathVariable Long id, Model model) {
        Recipe recipe = recipeService.getRecipeById(id);
        if (recipe != null) {
            model.addAttribute("recipe", recipe);
            List<Feedback> feedbacks = feedbackService.findByRecipe(recipe);
            model.addAttribute("feedbacks", feedbacks);
            return "recipe";
        } else {
            return "redirect:/";
        }
    }

    @GetMapping("/add")
    public String showAddRecipeForm(Model model) {
        List<Product> ingredients = productService.getAllProducts();

        model.addAttribute("ingredients", ingredients);

        model.addAttribute("createRecipeRequestDTO", new CreateRecipeRequestDTO());

        return "addRecipeForm";
    }

    @PostMapping("/save")
    public String saveRecipe(@ModelAttribute("recipeDto") CreateRecipeRequestDTO createRecipeRequestDTO) {
        recipeService.saveRecipe(createRecipeRequestDTO);

        return "redirect:/recipes";
    }

    @PostMapping("/{id}/addFeedback")
    public String addFeedback(@PathVariable Long id, @RequestParam String comment, Authentication authentication) {
        String username = getUsernameFromAuthentication(authentication);
        feedbackService.addFeedback(id, comment, username);
        return "redirect:/recipe/" + id;
    }

    private String getUsernameFromAuthentication(Authentication authentication) {
        if (authentication instanceof OAuth2AuthenticationToken oauthToken) {
            Map<String, Object> attributes = oauthToken.getPrincipal().getAttributes();
            return attributes.get("name").toString();
        } else {
            return authentication.getName();
        }
    }

}

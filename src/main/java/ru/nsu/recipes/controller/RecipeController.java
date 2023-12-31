package ru.nsu.recipes.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.nsu.recipes.dto.CreateRecipeRequestDTO;
import ru.nsu.recipes.entity.Feedback;
import ru.nsu.recipes.entity.Product;
import ru.nsu.recipes.entity.Recipe;
import ru.nsu.recipes.service.FeedbackService;
import ru.nsu.recipes.service.ProductService;
import ru.nsu.recipes.service.RecipeService;
import ru.nsu.recipes.service.UserUtilsService;

import java.util.List;

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

        model.addAttribute("allIngredients", ingredients);

        model.addAttribute("createRecipeRequestDTO", new CreateRecipeRequestDTO());

        return "addRecipeForm";
    }

    @PostMapping("/save")
    public String saveRecipe(@ModelAttribute("recipeDto") CreateRecipeRequestDTO createRecipeRequestDTO) {
        recipeService.saveRecipe(createRecipeRequestDTO);

        return "redirect:/";
    }

    @PostMapping("/{id}/addFeedback")
    public String addFeedback(@PathVariable Long id, @RequestParam String comment, Authentication authentication) {
        String username = UserUtilsService.getUsernameFromAuthentication(authentication);
        feedbackService.addFeedback(id, comment, username);
        return "redirect:/recipe/" + id;
    }

}

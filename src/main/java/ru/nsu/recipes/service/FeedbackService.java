package ru.nsu.recipes.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nsu.recipes.entity.Feedback;
import ru.nsu.recipes.entity.Recipe;
import ru.nsu.recipes.entity.RecipesUser;
import ru.nsu.recipes.repository.FeedbackRepository;
import ru.nsu.recipes.repository.RecipeRepository;
import ru.nsu.recipes.repository.RecipesUserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FeedbackService {

    private final FeedbackRepository feedbackRepository;

    private final RecipeRepository recipeRepository;

    private final RecipesUserRepository userRepository;

    public List<Feedback> findByRecipe(Recipe recipe) {
        return feedbackRepository.findAllByRecipe(recipe);
    }

    public void addFeedback(Long recipeId, String comment, String username) {
        Recipe recipe = recipeRepository.findById(recipeId).orElseThrow(() -> new RuntimeException("Рецепт не найден"));
        RecipesUser user = userRepository.findByUsername(username);

        Feedback feedback = new Feedback(comment, user, recipe);
        feedbackRepository.save(feedback);
    }
}

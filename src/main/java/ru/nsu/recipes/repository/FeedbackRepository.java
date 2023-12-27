package ru.nsu.recipes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nsu.recipes.entity.Feedback;
import ru.nsu.recipes.entity.Recipe;

import java.util.List;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {

    List<Feedback> findAllByRecipe(Recipe recipe);

}

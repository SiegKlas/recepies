package ru.nsu.recipes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nsu.recipes.entity.RecipesUser;

public interface RecipesUserRepository extends JpaRepository<RecipesUser, Long> {
    RecipesUser findByUsername(String username);
}

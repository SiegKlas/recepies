package ru.nsu.recipes.dto;

import lombok.Data;

import java.util.List;

@Data
public class CreateRecipeRequestDTO {

    private String recipeName;

    private String recipeDescription;

    private List<Long> productIds;

}

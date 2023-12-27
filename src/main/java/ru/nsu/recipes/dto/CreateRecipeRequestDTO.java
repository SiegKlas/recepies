package ru.nsu.recipes.dto;

import lombok.Data;

import java.util.List;

@Data
public class CreateRecipeRequestDTO {

    private String name;

    private String description;

    private List<Long> ingredientIds;

}

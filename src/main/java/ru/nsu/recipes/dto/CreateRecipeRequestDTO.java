package ru.nsu.recipes.dto;

import lombok.Data;

import java.util.List;

@Data
public class CreateRecipeRequestDTO {

    private String name;

    private String description;

    /**
     * Тут очень жесткий костыль: id приходят
     */
    private List<Long> ingredientIds;

}

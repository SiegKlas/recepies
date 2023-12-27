package ru.nsu.recipes.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class RecipesUser {

    @Id
    private Long id;

    @ManyToMany
    private List<Product> favouriteProducts = new ArrayList<>();

    @ManyToMany
    private List<Product> undesirableProducts = new ArrayList<>();

}

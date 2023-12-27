package ru.nsu.recipes.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Recipe {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String name;

    @Column(length = 10000)
    private String description;

    @OneToMany
    private final List<Feedback> feedbacks = new ArrayList<>();

    @ManyToMany
    private List<Product> products = new ArrayList<>();

}

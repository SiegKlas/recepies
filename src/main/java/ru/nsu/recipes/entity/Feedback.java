package ru.nsu.recipes.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@RequiredArgsConstructor
public class Feedback {
    @Column(length = 10000)
    private final String comment;
    @ManyToOne
    private final RecipesUser user;
    @ManyToOne
    private final Recipe recipe;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
}

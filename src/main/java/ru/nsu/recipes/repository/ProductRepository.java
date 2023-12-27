package ru.nsu.recipes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nsu.recipes.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}

package ru.nsu.recipes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nsu.recipes.entity.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByIdIn(List<Long> ids);

}

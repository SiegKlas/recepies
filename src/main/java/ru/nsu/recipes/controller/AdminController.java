package ru.nsu.recipes.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.nsu.recipes.entity.Product;
import ru.nsu.recipes.service.ProductService;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/admin")
public class AdminController {
    private final ProductService productService;

    @GetMapping("/products")
    public String showProducts(Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "product";
    }

    @PostMapping("/addProduct")
    public String addProduct(@RequestParam("productName") String productName) {
        Product product = new Product();
        product.setName(productName);
        productService.addProduct(product);
        return "redirect:/admin/products";
    }
}

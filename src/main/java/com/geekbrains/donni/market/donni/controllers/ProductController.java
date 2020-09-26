package com.geekbrains.donni.market.donni.controllers;

import com.geekbrains.donni.market.donni.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {
    private ProductService productService;

    @GetMapping
    public String showAllProducts (Model model, @RequestParam(defaultValue = "-1") int minPrice, @RequestParam(defaultValue = "-1") int maxPrice) {
        if (minPrice != -1 && maxPrice != -1) {
            model.addAttribute("products", productService.findAllByPriceBetween(minPrice, maxPrice));
            return "products";
        }
        if (minPrice != -1) {
            model.addAttribute("products", productService.findAllByPriceGreaterThanEqual(minPrice));
            return "products";
        }
        if (maxPrice != -1) {
            model.addAttribute("products", productService.findAllByPriceLessThanEqual(maxPrice));
            return "products";
        }
        model.addAttribute("products", productService.findAll());
        return "products";
    }
}

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
    public String showAllProducts (Model model) {
        model.addAttribute("products", productService.findAll());
        return "products";
    }

    @RequestMapping(value = "/min", method = RequestMethod.GET)
    public String showProductsByPriceGreaterThanEqual (Model model, @RequestParam int minPrice) {
        model.addAttribute("products", productService.findAllByPriceGreaterThanEqual(minPrice));
        return "products";
    }

    @RequestMapping(value = "/max", method = RequestMethod.GET)
    public String showProductsByPriceLessThanEqual (Model model, @RequestParam int maxPrice) {
        model.addAttribute("products", productService.findAllByPriceLessThanEqual(maxPrice));
        return "products";
    }

    @RequestMapping(value = "/range", method = RequestMethod.GET)
    public String showProductsByPriceBetween (Model model, @RequestParam int minPrice, @RequestParam int maxPrice) {
        model.addAttribute("products", productService.findAllByPriceBetween(minPrice, maxPrice));
        return "products";
    }
}

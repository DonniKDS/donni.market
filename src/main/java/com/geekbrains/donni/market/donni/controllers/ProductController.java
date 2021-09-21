package com.geekbrains.donni.market.donni.controllers;

import com.geekbrains.donni.market.donni.entities.Product;
import com.geekbrains.donni.market.donni.exceptions.ResourceNotFoundException;
import com.geekbrains.donni.market.donni.services.ProductService;
import com.geekbrains.donni.market.donni.util.ProductFilter;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {
    private ProductService productService;

    @GetMapping
    public String showAllProducts (Model model,
                                   @RequestParam(defaultValue = "1", name = "p") Integer page,
                                   @RequestParam Map<String, String> params) {
        if (page < 1) {
            page = 1;
        }
        ProductFilter productFilter = new ProductFilter(params);
        Page<Product> products = productService.findAll(productFilter.getSpec(), page - 1, 5);
        model.addAttribute("products", products);
        model.addAttribute("filterDefinition", productFilter.getFilterDefinition());
        return "products";
    }

    @GetMapping ("/edit/{id}")
    public String showEditForm (Model model, @PathVariable Long id) {
        Product product = productService.findById(id).orElseThrow(() ->new ResourceNotFoundException("Product not fount. ID " + id));
        model.addAttribute("product", product);
        return "edit_product";
    }

    @PostMapping("/edit")
    public String showEditForm (@ModelAttribute Product product) {
        productService.saveOrUpdate(product);
        return "redirect:/products";
    }
}

package com.geekbrains.donni.market.donni.controllers;

import com.geekbrains.donni.market.donni.entities.Product;
import com.geekbrains.donni.market.donni.exceptions.ResourceNotFoundException;
import com.geekbrains.donni.market.donni.services.ProductService;
import com.geekbrains.donni.market.donni.util.Cart;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping("/cart")
@AllArgsConstructor
public class CartController {
    private ProductService productService;
    private Cart cart;

    @GetMapping
    public String showCart (HttpSession session) {
        return "cart";
    }

    @GetMapping("/add/{product_id}")
    public void addToCart(
            @PathVariable(name = "product_id") Long productId,
            HttpServletRequest request, HttpServletResponse response
    ) throws IOException {
        Product product = productService.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product not fount. ID " + productId + ". (cart)"));
        cart.addOrIncrement(product);
        response.sendRedirect(request.getHeader("referer"));
    }

    @GetMapping("/inc/{product_id}")
    public String addOrIncrementProduct(@PathVariable(name = "product_id") Long productId) {
        cart.increment(productId);
        return "redirect:/cart";
    }

    @GetMapping("/dec/{product_id}")
    public String decrementOrRemoveProduct(@PathVariable(name = "product_id") Long productId) {
        cart.decrementOrRemove(productId);
        return "redirect:/cart";
    }

    @GetMapping("/remove/{product_id}")
    public String removeProduct(@PathVariable(name = "product_id") Long productId) {
        cart.remove(productId);
        return "redirect:/cart";
    }
}

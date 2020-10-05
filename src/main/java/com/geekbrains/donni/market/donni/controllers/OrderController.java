package com.geekbrains.donni.market.donni.controllers;

import com.geekbrains.donni.market.donni.entities.Order;
import com.geekbrains.donni.market.donni.services.OrderService;
import com.geekbrains.donni.market.donni.util.Cart;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/orders")
@AllArgsConstructor
public class OrderController {
    private OrderService orderService;
    private Cart cart;

    @GetMapping
    public String showOrder(Model model) {
        model.addAttribute("orders", orderService.findAll());
        return "orders";
    }

    @GetMapping("/create")
    public String createOrder(Model model) {
        model.addAttribute("order", new Order());
        return "create_order";
    }

    @GetMapping("/save")
    public String saveOrder(Order order) {
        order.setPrice(cart.getPrice());
        order.setItems(cart.getItems());
        orderService.save(order);
        return "redirect:/orders";
    }
}

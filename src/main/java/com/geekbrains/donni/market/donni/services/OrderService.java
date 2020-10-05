package com.geekbrains.donni.market.donni.services;

import com.geekbrains.donni.market.donni.entities.Order;
import com.geekbrains.donni.market.donni.entities.Product;
import com.geekbrains.donni.market.donni.repositories.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderService {
    private OrderRepository orderRepository;

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public void save(Order order) {
        order.getItems().forEach(orderItem -> orderItem.setOrder(order));
        orderRepository.save(order);
    }
}

package com.geekbrains.donni.market.donni.util;

import com.geekbrains.donni.market.donni.entities.OrderItem;
import com.geekbrains.donni.market.donni.entities.Product;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
@NoArgsConstructor
@Data
public class Cart {
    private List<OrderItem> items;
    private int price;

    @PostConstruct
    public void init() {
        items = new ArrayList<>();
    }

    public void addOrIncrement(Product product) {
        for (OrderItem orderItem : items) {
            if (orderItem.getProduct().getId().equals(product.getId())) {
                orderItem.incrementQuantity();
                recalculate();
                return;
            }
        }
        items.add(new OrderItem(product));
        recalculate();
    }

    public void increment(Long productId) {
        for (OrderItem orderItem : items) {
            if (orderItem.getProduct().getId().equals(productId)) {
                orderItem.incrementQuantity();
                recalculate();
                return;
            }
        }
    }

    public void decrementOrRemove(Long productId) {
        Iterator<OrderItem> iter = items.iterator();
        while (iter.hasNext()) {
            OrderItem orderItem = iter.next();
            if (orderItem.getProduct().getId().equals(productId)) {
                orderItem.decrementQuantity();
                if (orderItem.getQuantity() == 0) {
                    iter.remove();
                }
                recalculate();
                return;
            }
        }
    }

    public void remove(Long productId) {
        Iterator<OrderItem> iter = items.iterator();
        while (iter.hasNext()) {
            OrderItem orderItem = iter.next();
            if (orderItem.getProduct().getId().equals(productId)) {
                iter.remove();
                recalculate();
                return;
            }
        }
    }

    public void recalculate() {
        price = 0;
        for (OrderItem orderItem : items) {
            price += orderItem.getPrice();
        }
    }
}

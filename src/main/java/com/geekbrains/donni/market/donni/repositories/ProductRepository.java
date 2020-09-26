package com.geekbrains.donni.market.donni.repositories;

import com.geekbrains.donni.market.donni.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByPriceGreaterThanEqual (int minPrice);
    List<Product> findAllByPriceLessThanEqual (int maxPrice);
    List<Product> findAllByPriceBetween (int minPrice, int maxPrice);
}

package com.techie.microservices.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techie.microservices.inventory.model.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    boolean existsBySkuCodeAndQuantityIsGreaterThanEqual(String skuCode, Integer quantity);
    // List<Inventory> findBySkuCodeIn(List<String> skuCode);
}
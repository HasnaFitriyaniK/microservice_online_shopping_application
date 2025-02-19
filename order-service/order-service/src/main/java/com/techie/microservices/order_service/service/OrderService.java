package com.techie.microservices.order_service.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.techie.microservices.order_service.dto.OrderRequest;
import com.techie.microservices.order_service.model.Order;
import com.techie.microservices.order_service.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final InventoryClient inventoryClient;

    public void placeOrder(OrderRequest OrderRequest) {

        
        var isProductInStock = inventoryClient.isInStock(OrderRequest.skuCode(), OrderRequest.quantity());

        if (isProductInStock) {
            // map OrderRequest to Order object
            Order order = new Order();
            order.setOrderNumber(UUID.randomUUID().toString());
            order.setSkuCode(OrderRequest.skuCode());
            order.setPrice(OrderRequest.price());
            order.setQuantity(OrderRequest.quantity());
            // save order to OrderRepository
            orderRepository.save(order);
        } else {
            throw new RuntimeException("Product with SkuCode " + orderRequest.skuCode() + " is not in stock.");
        }
    }
}

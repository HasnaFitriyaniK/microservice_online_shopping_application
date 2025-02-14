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
    public void placeOrder(OrderRequest OrderRequest){
        //map OrderRequest to Order object
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        order.setSkuCode(OrderRequest.skuCode());
        order.setPrice(OrderRequest.price());
        order.setQuantity(OrderRequest.quantity());
        //save order to OrderRepository
        orderRepository.save(order);
    }
}

package org.example.service.impl;

import org.example.Models.Order;
import org.example.dto.OrderDto;
import org.example.repositories.OrderRepository;
import org.example.service.OrderService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderServiceImpl implements OrderService {
    private OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public void save(OrderDto orderDto) {
        Order order = Order.builder()
                .userId(orderDto.getUserId())
                .burgerid(orderDto.getBurgerid())
                .quantity(orderDto.getQuantity())
                .build();
        orderRepository.save(order);
    }

    @Override
    public void delete(Long orderId) {
        orderRepository.remove(orderId);
    }

    @Override
    public List<OrderDto> findOrdersByUserId(Long userId){
        List<OrderDto> orderDtos = new ArrayList<>();
        List<Order> orders = orderRepository.findByUser(userId);
        for (Order order : orders) {
            orderDtos.add(OrderDto.builder()
                    .orderId(order.getId())
                    .burgerid(order.getBurgerid())
                    .userId(order.getUserId())
                    .quantity(order.getQuantity())
                    .build());
        }return orderDtos;
    }

    @Override
    public void update(OrderDto orderDto)  {
        Order order = Order.builder()
                .userId(orderDto.getUserId())
                .burgerid(orderDto.getBurgerid())
                .quantity(orderDto.getQuantity())
                .id(orderDto.getOrderId())
                .build();
        orderRepository.update(order);
    }
}

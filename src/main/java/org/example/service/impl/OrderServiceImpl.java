package org.example.service.impl;

import org.example.Models.Order;
import org.example.repositories.OrderRepository;
import org.example.service.OrderService;

import java.sql.SQLException;
import java.util.List;

public class OrderServiceImpl implements OrderService {
    private OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void save(Order order) throws SQLException {
        orderRepository.save(order);
    }

    public void delete(Long orderId) throws SQLException {
        orderRepository.remove(orderId);
    }

    public List<Order> findOrdersByUserId(Long userId) throws SQLException {
        return orderRepository.findByUser(userId);
    }

    public List<Order> findAllOrders() throws SQLException {
        return orderRepository.findAll();
    }



}

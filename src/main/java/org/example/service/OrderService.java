package org.example.service;

import org.example.Models.Order;

import java.sql.SQLException;
import java.util.List;

public interface OrderService {
    void save(Order order) throws SQLException;

    void delete(Long orderId) throws SQLException;
    List<Order> findOrdersByUserId(Long userId) throws SQLException;

    List<Order> findAllOrders() throws SQLException;
}

package org.example.service;

import org.example.dto.OrderDto;

import java.sql.SQLException;
import java.util.List;

public interface OrderService {
    void save(OrderDto orderDto) throws SQLException;
    void delete(Long orderId) throws SQLException;
    List<OrderDto> findOrdersByUserId(Long userId) throws SQLException;
    void update(OrderDto orderDto) throws SQLException;
}

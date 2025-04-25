package org.example.service;

import org.example.dto.OrderDto;

import java.sql.SQLException;
import java.util.List;

public interface OrderService {
    void save(OrderDto orderDto);
    void delete(Long orderId) ;
    List<OrderDto> findOrdersByUserId(Long userId);
    void update(OrderDto orderDto);
}

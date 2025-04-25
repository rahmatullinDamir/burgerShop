package org.example.repositories;


import org.example.Models.Order;

import java.util.List;

public interface OrderRepository extends CrudRepository<Order> {
    List<Order> findByUser(Long id);


}

package org.example.repositories;

import org.example.Models.Burger;

import java.sql.SQLException;
import java.util.Optional;

public interface BurgerRepository extends CrudRepository<Burger> {
    Optional<Burger> findByName(String name) throws SQLException;
}

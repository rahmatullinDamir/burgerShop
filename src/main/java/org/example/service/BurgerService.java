package org.example.service;

import org.example.Models.Burger;
import org.example.dto.BurgerDto;

import java.sql.SQLException;
import java.util.List;

public interface BurgerService {
    List<BurgerDto> findAll();

    BurgerDto findById(Long id) throws SQLException;

    void deleteById(Long id) throws SQLException;

    void save(BurgerDto burgerDto) throws SQLException;

    BurgerDto findByName(String name) throws SQLException;
}

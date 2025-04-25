package org.example.service;

import org.example.Models.Burger;
import org.example.dto.BurgerDto;

import java.sql.SQLException;
import java.util.List;

public interface BurgerService {
    List<BurgerDto> findAll();

    BurgerDto findById(Long id);

    void deleteById(Long id);

    void save(BurgerDto burgerDto);

    BurgerDto findByName(String name);
}

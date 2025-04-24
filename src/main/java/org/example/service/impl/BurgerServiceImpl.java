package org.example.service.impl;

import org.example.Models.Burger;
import org.example.dto.BurgerDto;
import org.example.repositories.BurgerRepository;
import org.example.repositories.UserRepository;
import org.example.service.BurgerService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BurgerServiceImpl implements BurgerService {
    private BurgerRepository burgerRepository;

    public BurgerServiceImpl(BurgerRepository burgerRepository) {
        this.burgerRepository = burgerRepository;
    }


    @Override
    public List<BurgerDto> findAll() {
        List<BurgerDto> burgerDtos = new ArrayList<>();
        try {
            List<Burger> burgers = burgerRepository.findAll();
            for (Burger burger: burgers) {
                burgerDtos.add(BurgerDto.builder()
                        .price(burger.getPrice())
                        .name(burger.getName())
                        .description(burger.getDescription())
                        .build());
            }
        } catch (SQLException exception) {
            System.out.println(exception);
        }
        return burgerDtos;
    }

    @Override
    public BurgerDto findById(Long id) throws SQLException {
        Optional<Burger> burger = burgerRepository.findById(id);
        if (burger.isPresent()) {
            return BurgerDto.builder()
                    .description(burger.get().getDescription())
                    .price(burger.get().getPrice())
                    .name(burger.get().getName())
                    .build();
        }
        return null;
    }

    @Override
    public void update(Burger burger) throws SQLException {
        burgerRepository.update(burger);
    }

    @Override
    public void deleteById(Long id) throws SQLException {
        burgerRepository.remove(id);
    }

    @Override
    public void save(Burger burger) throws SQLException {
        burgerRepository.save(burger);
    }
}

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
        List<Burger> burgers = burgerRepository.findAll();
        for (Burger burger : burgers) {
            burgerDtos.add(BurgerDto.builder()
                    .id(burger.getId())
                    .price(burger.getPrice())
                    .name(burger.getName())
                    .description(burger.getDescription())
                    .build());
        }

        return burgerDtos;
    }

    @Override
    public BurgerDto findById(Long id) {
        Optional<Burger> burger = burgerRepository.findById(id);
        if (burger.isPresent()) {
            return BurgerDto.builder()
                    .id(burger.get().getId())
                    .description(burger.get().getDescription())
                    .price(burger.get().getPrice())
                    .name(burger.get().getName())
                    .build();
        }
        return null;
    }


    @Override
    public void deleteById(Long id){
        burgerRepository.remove(id);
    }

    @Override
    public void save(BurgerDto burgerDto) {
        Burger burger = Burger.builder()
                .name(burgerDto.getName())
                .description(burgerDto.getDescription())
                .price(burgerDto.getPrice())
                .build();
        burgerRepository.save(burger);
    }

    @Override
    public BurgerDto findByName(String name)  {
        Optional<Burger> optionalBurger = burgerRepository.findByName(name);
        if (optionalBurger.isPresent()) {
            Burger burger = optionalBurger.get();
            return BurgerDto.builder()
                    .id(burger.getId())
                    .description(burger.getDescription())
                    .price(burger.getPrice())
                    .name(burger.getName())
                    .build();
        }
        return null;

    }
}

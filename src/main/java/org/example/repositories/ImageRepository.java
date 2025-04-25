package org.example.repositories;

import org.example.Models.Image;

import java.util.Optional;

public interface ImageRepository extends CrudRepository<Image> {
    Optional<Image> findBurgerById(Long id);

    Optional<Image> findIdByImage(String originalName, Long burgerid, Long size);

}

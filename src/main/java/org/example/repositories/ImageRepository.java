package org.example.repositories;

import org.example.Models.Image;

import java.sql.SQLException;
import java.util.Optional;

public interface ImageRepository extends CrudRepository<Image> {
    Optional<Image> findBurgerById(Long id) throws SQLException;

    Optional<Image> findIdByImage(String originalName, Long burgerid, Long size) throws SQLException;

}

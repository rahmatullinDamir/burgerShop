package org.example.repositories;

import org.example.Models.Image;

import java.sql.SQLException;
import java.util.Optional;

public interface ImageRepository extends CrudRepository<Image> {
    Optional<Image> findDayById(Long id) throws SQLException;

    Optional<Image> findIdByImage(String originalName, Long dayId, Long size) throws SQLException;

}

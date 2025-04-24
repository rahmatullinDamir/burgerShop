package org.example.repositories;

import org.example.Models.User;

import java.sql.SQLException;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User> {

    Optional<User> findByUsername(String username) throws SQLException;
}

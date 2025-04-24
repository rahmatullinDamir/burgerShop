package org.example.repositories.impl;

import org.example.Models.User;
import org.example.repositories.UserRepository;

import javax.sql.DataSource;
import java.sql.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepositoryJdbcImpl implements UserRepository {

    private static final String SQL_SELECT_BY_USERNAME = "SELECT * FROM users WHERE username = ?";
    private static final String SQL_SELECT_ALL = "SELECT * FROM users";
    private static final String SQL_SELECT_FROM_USERS_BY_ID = "SELECT * FROM users WHERE id = ?";
    private static final String SQL_INSERT_NEW_USER = "INSERT INTO users (username, password) VALUES (?, ?)";
    private static final String SQL_DELETE_USER = "DELETE FROM users WHERE id = ?";

    private DataSource dataSource;

    public UserRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;

    }



    @Override
    public Optional<User> findById(Long id) throws SQLException {
        Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(SQL_SELECT_FROM_USERS_BY_ID);
        statement.setLong(1, id);

        ResultSet resultSet = statement.executeQuery();


        if (resultSet.next()) {
            return Optional.of(new User(resultSet.getLong("id"),
                    resultSet.getString("username"),
                    resultSet.getString("password"),
                    resultSet.getString("role")));
        }

        return Optional.empty();
    }

    @Override
    public List<User> findAll() throws SQLException {
        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL);

        List<User> result = new ArrayList<>();

        while (resultSet.next()) {
            User user = new User(resultSet.getLong("id"),
                    resultSet.getString("username"),
                    resultSet.getString("password"),
                    resultSet.getString("role"));
            result.add(user);
        }

        return result;
    }


    @Override
    public void save(User entity) throws SQLException {
        Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(SQL_INSERT_NEW_USER);
        statement.setString(1, entity.getUsername());
        statement.setString(2, entity.getPassword());
        statement.executeUpdate();
    }

    @Override
    public void update(User entity) throws SQLException {

    }


    @Override
    public void remove(Long id) throws SQLException {
        Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(SQL_DELETE_USER);
        statement.setLong(1, id);
        statement.executeUpdate();
    }

    @Override
    public Optional<User> findByUsername(String username) throws SQLException {
        Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_USERNAME);
        statement.setString(1, username);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            return Optional.of(new User(resultSet.getLong("id"),
                    resultSet.getString("username"),
                    resultSet.getString("password"),
                    resultSet.getString("role")));
        }

        return Optional.empty();

    }
}

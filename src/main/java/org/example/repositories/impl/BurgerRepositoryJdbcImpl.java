package org.example.repositories.impl;

import org.example.Models.Burger;
import org.example.repositories.BurgerRepository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BurgerRepositoryJdbcImpl implements BurgerRepository {

    private final DataSource dataSource;

    public BurgerRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Optional<Burger> findById(Long id)  {
        String sql = "SELECT * FROM burger WHERE id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(mapRowToBurger(resultSet));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public List<Burger> findAll() {
        List<Burger> burgers = new ArrayList<>();
        String sql = "SELECT * FROM burger";
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                burgers.add(mapRowToBurger(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return burgers;
    }

    @Override
    public Optional<Burger> findByName(String name) {
        try (Connection connection = dataSource.getConnection()){
            String sql = "SELECT * FROM burger WHERE name = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return Optional.of(mapRowToBurger(resultSet));
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void save(Burger burger) {
        String sql = "INSERT INTO burger (name, price, description) VALUES (?, ?, ?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, burger.getName());
            statement.setInt(2, burger.getPrice());
            statement.setString(3, burger.getDescription());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Burger burger) {
        String sql = "UPDATE burger SET name = ?, price = ?, description = ? WHERE id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, burger.getName());
            statement.setDouble(2, burger.getPrice());
            statement.setString(3, burger.getDescription());
            statement.setLong(4, burger.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void remove(Long id) {
        String sql = "DELETE FROM burger WHERE id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Burger mapRowToBurger(ResultSet resultSet) throws SQLException {
        Burger burger = Burger.builder()
                .id(resultSet.getLong("id"))
                .name(resultSet.getString("name"))
                .description(resultSet.getString("description"))
                .price(resultSet.getInt("price"))
                .build();
        return burger;
    }


}
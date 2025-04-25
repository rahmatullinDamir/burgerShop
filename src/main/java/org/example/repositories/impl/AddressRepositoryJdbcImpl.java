package org.example.repositories.impl;

import org.example.Models.Address;
import org.example.repositories.AddressRepository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AddressRepositoryJdbcImpl implements AddressRepository {
    private static final String SQL_SELECT_FROM_ADDRESS_BY_ID = "SELECT * FROM Address WHERE id = ?";
    private static final String SQL_SELECT_ALL = "SELECT * FROM Address";
    private static final String SQL_INSERT_NEW_ADDRESS = "INSERT INTO Address (street, city, house, flat) VALUES (?, ?, ?, ?)";
    private static final String SQL_UPDATE_ADDRESS = "UPDATE Address SET street = ?, city = ?, house = ?, flat = ? WHERE id = ?";
    private static final String SQL_DELETE_ADDRESS = "DELETE FROM Address WHERE id = ?";
    private static final String SQL_SELECT_ID_BY_ADDRESS = "SELECT * FROM Address WHERE street = ? AND city = ? AND house = ? AND flat = ?";

    private DataSource dataSource;

    public AddressRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Optional<Address> findById(Long id) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SQL_SELECT_FROM_ADDRESS_BY_ID);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(Address.builder()
                        .id(resultSet.getLong("id"))
                        .street(resultSet.getString("street"))
                        .city(resultSet.getString("city"))
                        .house(resultSet.getLong("house"))
                        .flat(resultSet.getLong("flat"))
                        .build());
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Address> findAll() {
        try (Connection connection = dataSource.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL);

            List<Address> addresses = new ArrayList<>();

            while (resultSet.next()) {
                addresses.add(Address.builder()
                        .id(resultSet.getLong("id"))
                        .street(resultSet.getString("street"))
                        .city(resultSet.getString("city"))
                        .house(resultSet.getLong("house"))
                        .flat(resultSet.getLong("flat"))
                        .build());
            }
            return addresses;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void save(Address entity) {
        try (Connection connection = dataSource.getConnection()){
            PreparedStatement statement = connection.prepareStatement(SQL_INSERT_NEW_ADDRESS);
            statement.setString(1, entity.getStreet());
            statement.setString(2, entity.getCity());
            statement.setLong(3, entity.getHouse());
            statement.setLong(4, entity.getFlat());
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void update(Address entity) {
        try (Connection connection = dataSource.getConnection()){
            PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_ADDRESS);
            statement.setString(1, entity.getStreet());
            statement.setString(2, entity.getCity());
            statement.setLong(3, entity.getHouse());
            statement.setLong(4, entity.getFlat());
            statement.setLong(5, entity.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void remove(Long id) {
        try (Connection connection = dataSource.getConnection()){
            PreparedStatement statement = connection.prepareStatement(SQL_DELETE_ADDRESS);
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Long findIdByAddress(Address address) {
        try(Connection connection = dataSource.getConnection()){
            PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ID_BY_ADDRESS);
            statement.setString(1, address.getStreet());
            statement.setString(2, address.getCity());
            statement.setLong(3, address.getHouse());
            statement.setLong(4, address.getFlat());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getLong(1);
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
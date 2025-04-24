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

    private DataSource dataSource;

    public AddressRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Optional<Address> findById(Long id) throws SQLException {
        Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(SQL_SELECT_FROM_ADDRESS_BY_ID);
        statement.setLong(1, id);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            return Optional.of(Address.builder()
                    .id(resultSet.getInt("id"))
                    .street(resultSet.getString("street"))
                    .city(resultSet.getString("city"))
                    .house(resultSet.getString("house"))
                    .flat(resultSet.getString("flat"))
                    .build());
        }
        return Optional.empty();
    }

    @Override
    public List<Address> findAll() throws SQLException {
        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL);

        List<Address> addresses = new ArrayList<>();

        while (resultSet.next()) {
            addresses.add(Address.builder()
                    .id(resultSet.getInt("id"))
                    .street(resultSet.getString("street"))
                    .city(resultSet.getString("city"))
                    .house(resultSet.getString("house"))
                    .flat(resultSet.getString("flat"))
                    .build());
        }
        return addresses;
    }

    @Override
    public void save(Address entity) throws SQLException {
        Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(SQL_INSERT_NEW_ADDRESS);
        statement.setString(1, entity.getStreet());
        statement.setString(2, entity.getCity());
        statement.setString(3, entity.getHouse());
        statement.setString(4, entity.getFlat());
        statement.execute();
    }

    @Override
    public void update(Address entity) throws SQLException {
        Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_ADDRESS);
        statement.setString(1, entity.getStreet());
        statement.setString(2, entity.getCity());
        statement.setString(3, entity.getHouse());
        statement.setString(4, entity.getFlat());
        statement.setInt(5, entity.getId());
        statement.executeUpdate();
    }

    @Override
    public void remove(Long id) throws SQLException {
        Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(SQL_DELETE_ADDRESS);
        statement.setLong(1, id);
        statement.executeUpdate();
    }
}
package org.example.repositories.impl;

import org.example.Models.Order;
import org.example.repositories.OrderRepository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderRepositoryJdbcImpl implements OrderRepository {
    private static final String SQL_SELECT_FROM_ORDER_BY_ID = "SELECT * FROM orders WHERE id = ?";
    private static final String SQL_SELECT_ALL = "SELECT * FROM orders";
    private static final String SQL_SELECT_FROM_ORDER_BY_USER = "SELECT * FROM orders WHERE userid = ?";
    private static final String SQL_INSERT_NEW_ORDER = "INSERT INTO orders (userid, addressid, burgerid, quantity) VALUES (?, ?, ?, ?)";
    private static final String SQL_UPDATE_ORDER = "UPDATE orders SET userid = ?, burgerid = ?, quantity = ? WHERE id = ?";
    private static final String SQL_DELETE_ORDER = "DELETE FROM orders WHERE id = ?";

    private DataSource dataSource;

    public OrderRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Order> findByUser(Long id) throws SQLException {
        Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(SQL_SELECT_FROM_ORDER_BY_USER);
        statement.setLong(1, id);
        ResultSet resultSet = statement.executeQuery();
        List<Order> Orders = new ArrayList<>();
        while (resultSet.next()) {
            Orders.add(new Order(  resultSet.getLong("id"),
                    resultSet.getLong("userid"),
                    resultSet.getLong("burgerid"),
                    resultSet.getLong("quantity")));
        }
        return Orders;
    }


    @Override
    public List<Order> findAll() throws SQLException {
        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL);


        List<Order> Orders = new ArrayList<>();

        while (resultSet.next()) {
            Order Order = new Order(  resultSet.getLong("id"),
                    resultSet.getLong("userid"),
                    resultSet.getLong("burgerid"),
                    resultSet.getLong("quantity"));
            Orders.add(Order);
        }
        return Orders;
    }

    @Override
    public Optional<Order> findById(Long id) throws SQLException {
        Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(SQL_SELECT_FROM_ORDER_BY_ID);
        statement.setLong(1, id);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            return Optional.of(new Order(
                    resultSet.getLong("id"),
                    resultSet.getLong("userid"),
                    resultSet.getLong("burgerid"),
                    resultSet.getLong("quantity")));
        }
        return Optional.empty();
    }

    @Override
    public void save(Order entity) throws SQLException {
        Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(SQL_INSERT_NEW_ORDER);
        statement.setLong(1, entity.getUserId());
        statement.setLong(2, entity.getBurgerid());
        statement.setLong(3, entity.getQuantity());
        statement.execute();
    }

    @Override
    public void update(Order entity) throws SQLException {
        Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_ORDER);
        statement.setLong(1, entity.getUserId());
        statement.setLong(2, entity.getBurgerid());
        statement.setLong(3, entity.getQuantity());
        statement.setLong(4, entity.getId());
        statement.executeUpdate();
    }


    @Override
    public void remove(Long id) throws SQLException {
        Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(SQL_DELETE_ORDER);
        statement.setLong(1, id);
        statement.executeUpdate();
    }


}

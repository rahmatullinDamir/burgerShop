package org.example.repositories.impl;

import org.example.Models.Order;
import org.example.repositories.OrderRepository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderRepositoryJdbcImpl implements OrderRepository {
    private static final String SQL_SELECT_FROM_Order_BY_ID = "SELECT * FROM Order WHERE id = ?";
    private static final String SQL_SELECT_ALL = "SELECT * FROM Order";
    private static final String SQL_SELECT_FROM_Order_BY_USER = "SELECT * FROM Order WHERE user_id = ?";
    private static final String SQL_INSERT_NEW_Order = "INSERT INTO Order (userid, addressid) VALUES (?, ?)";
    private static final String SQL_UPDATE_Order = "UPDATE Order SET userid = ?, address = ? WHERE id = ?";
    private static final String SQL_DELETE_Order = "DELETE FROM Order WHERE id = ?";

    private DataSource dataSource;

    public OrderRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Order> findByUser(Long id) throws SQLException {
        Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(SQL_SELECT_FROM_Order_BY_USER);
        statement.setLong(1, id);
        ResultSet resultSet = statement.executeQuery();
        List<Order> Orders = new ArrayList<>();
        while (resultSet.next()) {
            Orders.add(new Order(
                    resultSet.getLong("id"),
                    resultSet.getLong("userid"),
                    resultSet.getLong("addressid")
            ));
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
            Order Order = new Order(resultSet.getLong("id"),
                    resultSet.getLong("userid"),
                    resultSet.getLong("addressid"));
            Orders.add(Order);
        }
        return Orders;
    }

    @Override
    public Optional<Order> findById(Long id) throws SQLException {
        Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(SQL_SELECT_FROM_Order_BY_ID);
        statement.setLong(1, id);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            return Optional.of(new Order(
                    resultSet.getLong("id"),
                    resultSet.getLong("userid"),
                    resultSet.getLong("addressid")));
        }
        return Optional.empty();
    }

    @Override
    public void save(Order entity) throws SQLException {
        Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(SQL_INSERT_NEW_Order);
        statement.setLong(1, entity.getUserId());
        statement.setLong(2, entity.getAddressId());
        statement.execute();
    }

    @Override
    public void update(Order entity) throws SQLException {
        Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_Order);
        statement.setLong(1, entity.getUserId());
        statement.setLong(2, entity.getAddressId());
        statement.executeUpdate();
    }


    @Override
    public void remove(Long id) throws SQLException {
        Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(SQL_DELETE_Order);
        statement.setLong(1, id);
        statement.executeUpdate();
    }


}

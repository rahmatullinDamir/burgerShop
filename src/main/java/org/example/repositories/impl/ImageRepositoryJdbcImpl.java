package org.example.repositories.impl;

import org.example.Models.Image;
import org.example.repositories.ImageRepository;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ImageRepositoryJdbcImpl implements ImageRepository {

    private JdbcTemplate jdbcTemplate;
    private DataSource dataSource;

    public ImageRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    //language=SQL
    private final static String SQL_SELECT_BY_PARAM_ = "select * from image where originalfilename = ? and burgerId = ? and size = ? order by id desc limit 1";
    //language=SQL
    private final static String SQL_INSERT = "insert into image(originalfilename, storagefilename, type, burgerid, size) " + "values (?, ?, ?, ?, ?)";
    //language=SQL
    private final static String SQL_SELECT_BY_ID = "select * from image where id = ?";
    //language=SQL
    private final static String SQL_SELECT_BY_BURGER_ID = "select * from image where burgerid = ?";
    //language=SQL
    private final static String SQL_DELETE = "delete from image where id = ?";

    private RowMapper<Image> fileRowMapper = (row, rowNumber) ->
            Image.builder()
                    .id(row.getLong("id"))
                    .originalFileName(row.getString("originalfilename"))
                    .storageFileName(row.getString("storagefilename"))
                    .type(row.getString("type"))
                    .burgerid(row.getLong("burgerid"))
                    .size(row.getLong("size"))
                    .build();

    @Override
    public Optional<Image> findById(Long id) {
        Image image = jdbcTemplate.queryForObject(SQL_SELECT_BY_ID, fileRowMapper, id);
        return Optional.of(image);

    }

    @Override
    public List<Image> findAll() {
        return null;
    }

    @Override
    public void save(Image entity) {
        jdbcTemplate.update(SQL_INSERT, entity.getOriginalFileName(), entity.getStorageFileName(),
                entity.getType(),
                entity.getBurgerid(),
                entity.getSize());
    }

    @Override
    public void update(Image entity) {

    }


    @Override
    public void remove(Long id) {
        try (Connection connection = dataSource.getConnection()){
            PreparedStatement statement = connection.prepareStatement(SQL_DELETE);
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    @Override
    public Optional<Image> findBurgerById(Long id) {
        try (Connection connection = dataSource.getConnection()){
            PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_BURGER_ID);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(Image.builder()
                        .id(resultSet.getLong("id"))
                        .originalFileName(resultSet.getString("originalfilename"))
                        .storageFileName(resultSet.getString("storagefilename"))
                        .type(resultSet.getString("type"))
                        .burgerid(resultSet.getLong("burgerid"))
                        .size(resultSet.getLong("size"))
                        .build());
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Optional<Image> findIdByImage(String originalName, Long dayId, Long size)  {
        try (Connection connection = dataSource.getConnection()){
            PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_PARAM_);
            statement.setString(1, originalName);
            statement.setLong(2, dayId);
            statement.setLong(3, size);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(Image.builder()
                        .id(resultSet.getLong("id"))
                        .originalFileName(resultSet.getString("originalfilename"))
                        .storageFileName(resultSet.getString("storagefilename"))
                        .type(resultSet.getString("type"))
                        .burgerid(resultSet.getLong("burgerid"))
                        .size(resultSet.getLong("size"))
                        .build());
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


}

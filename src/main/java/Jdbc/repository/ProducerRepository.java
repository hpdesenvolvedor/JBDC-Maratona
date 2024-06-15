package Jdbc.repository;

import Jdbc.conn.ConnectionFactory;
import Jdbc.dominio.Producer;
import lombok.extern.log4j.Log4j2;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Log4j2

public class ProducerRepository {
    public static void save(Producer producer) {
        String sql = "INSERT INTO `anime_store`.`producer` (`name`) VALUES ('%s');".formatted(producer.getName());
        try (Connection coon = ConnectionFactory.getConnection();
             Statement stm = coon.createStatement()) {
            int rowsAffected = stm.executeUpdate(sql);
            log.info("Inserted producer '{}' in the database, rows affected '{}'", producer.getName(), rowsAffected);
        } catch (SQLException e) {
            log.error("Error while trying to insert producer '{}'", producer.getName(), e);
            e.printStackTrace();
        }
    }

    public static void delete(int id) {
        String sql = "DELETE FROM `anime_store`.`producer` WHERE (`id` = '%d');".formatted(id);
        try (Connection coon = ConnectionFactory.getConnection();
             Statement stm = coon.createStatement()) {
            int rowsAffected = stm.executeUpdate(sql);
            log.info("Deleted producer '{}' from the database, rows affected '{}'", id, rowsAffected);
        } catch (SQLException e) {
            log.error("Error while trying to delete producer '{}'", id, e);
        }
    }

    public static void update(Producer producer) {
        String sql = "UPDATE `anime_store`.`producer` SET `name` = '%s' WHERE (`id` = '%d');\n".formatted(producer.getName(), producer.getId());
        try (Connection coon = ConnectionFactory.getConnection();
             Statement stm = coon.createStatement()) {
            int rowsAffected = stm.executeUpdate(sql);
            log.info("Update producer '{}', rows affected '{}'", producer.getId(), rowsAffected);
        } catch (SQLException e) {
            log.error("Error while trying to update producer '{}'", producer.getId(), e);
        }
    }

    public static List<Producer> findAll() {
        log.info("Finding all Producers");
        return findByName("String");
    }

    public static List<Producer> findByName(String name) {
        log.info("Finding Producers by name");
        String sql = "SELECT * FROM anime_store.producer; where name like '%%%s%%';".formatted(name);
        List<Producer> producers = new ArrayList<>();
        try (Connection coon = ConnectionFactory.getConnection();
             Statement stm = coon.createStatement();
             ResultSet rs = stm.executeQuery(sql)) {
            while (rs.next()) {
                Producer producer = Producer
                        .builder()
                        .id(rs.getInt("id"))
                        .name(rs.getString("name"))
                        .build();
                producers.add(producer);
            }
        } catch (SQLException e) {
            log.error("Error while trying to find all producers", e);
        }
        return producers;
    }

    public static void showProducerMetaData() {
        log.info("Show Producers Metadate");
        String sql = "SELECT * FROM anime_store.producer; where name like '%%%s%%';";
        try (Connection coon = ConnectionFactory.getConnection();
             Statement stm = coon.createStatement();
             ResultSet rs = stm.executeQuery(sql)) {
            ResultSetMetaData rsmetaData = rs.getMetaData();
            int columnCount = rsmetaData.getColumnCount();
            log.info("Columns cout '{}", columnCount);
            for (int i = 0; i < columnCount; i++) {
                log.info("Table name '{}", rsmetaData.getTableName(i));
                log.info("Table name '{}", rsmetaData.getColumnName(i));
                log.info("Table name '{}", rsmetaData.getColumnDisplaySize(i));
                log.info("Table name '{}", rsmetaData.getColumnType(i));
            }

        } catch (SQLException e) {
            log.error("Error while trying to find all producers", e);
        }

    }

    public static void showDriverMetaData() {
        log.info("Show Driver Metadate");
        try (Connection coon = ConnectionFactory.getConnection()) {
            DatabaseMetaData dbMetaData = coon.getMetaData();
            if (dbMetaData.supportsResultSetType(ResultSet.TYPE_FORWARD_ONLY)) {
                log.info("Supports TYPE_FORWARD_ONLY");
                if (dbMetaData.supportsResultSetConcurrency(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE)) {
                    log.info("And Supports CONCUR_UPDATABLE");
                }
            }

            if (dbMetaData.supportsResultSetType(ResultSet.TYPE_SCROLL_INSENSITIVE)) {
                log.info("Supports TYPE_SCROLL_INSENSITIVE");
                if (dbMetaData.supportsResultSetType(ResultSet.TYPE_SCROLL_INSENSITIVE)) {
                    log.info("Supports CONCUR_UPDATABLE");
                }
            }

            if (dbMetaData.supportsResultSetType(ResultSet.TYPE_SCROLL_SENSITIVE)) {
                log.info("Supports TYPE_SCROLL_SENSITIVE");
                if (dbMetaData.supportsResultSetType(ResultSet.TYPE_SCROLL_SENSITIVE)) {
                    log.info("Supports CONCUR_UPDATABLE");
                }
            }
        } catch (
                SQLException e) {
            log.error("Error while trying to find all producers", e);
        }

    }

    public static void showTypeScrollWorking() {
        String sql = "SELECT * FROM anime_store.producer;";
        try (Connection coon = ConnectionFactory.getConnection();
             Statement stm = coon.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
             ResultSet rs = stm.executeQuery(sql)) {
            log.info("Last row? '{}'", rs.last());
            log.info(Producer.builder().id(rs.getInt("id")).name(rs.getString("name")).build());
        } catch (SQLException e) {
            log.error("Error while trying to find all producers", e);
        }
    }

}



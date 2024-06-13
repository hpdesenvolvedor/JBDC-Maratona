package Jdbc.repository;
import Jdbc.conn.ConnectionFactory;
import Jdbc.dominio.Producer;
import lombok.extern.log4j.Log4j2;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@Log4j2

public class ProducerRepository {
    public static void save(Producer producer){
        String sql = "INSERT INTO `anime_store`.`producer` (`name`) VALUES ('%');".formatted(producer.getName());
        try(Connection coon = ConnectionFactory.getConnection();
            Statement stm = coon.createStatement()){
            int rowsAffected = stm.executeUpdate(sql);
            log.info("Inserted producer database, rows affected '{}'", rowsAffected);
        }catch (SQLException e){
            e.printStackTrace();
        }
}
}

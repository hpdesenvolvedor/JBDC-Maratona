package Jdbc.test;
import Jdbc.dominio.Producer;
import Jdbc.repository.ProducerRepository;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class ConnectionFactoryTest01 {
    public static void main(String[] args) {
     Producer producer = Producer.builder().name("Studio Deen").build();
     ProducerRepository.save(producer);
      log.info("info");
      log.debug("debug");
      log.warn("warn");
      log.error("error");
      log.trace("trace");
    }
}

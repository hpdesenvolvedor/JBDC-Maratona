package Jdbc.service;
import Jdbc.dominio.Producer;
import Jdbc.repository.ProducerRepository;

import java.util.List;

public class ProducerService {
    public static void save(Producer producer){
        ProducerRepository.save(producer);
    }
    public static void delete(Integer id) throws IllegalAccessException {
        requireValidId(id);
        ProducerRepository.delete(id);
    }
    public static void update(Producer producer) throws IllegalAccessException {
        requireValidId(producer.getId());
        ProducerRepository.update(producer);
    }
    public static List<Producer> findAll() throws IllegalAccessException {
        return ProducerRepository.findAll();
    }
    public static List<Producer> findByName(String name) throws IllegalAccessException {
        return ProducerRepository.findByName(name);
    }
    public static void showProducerMetaData() throws IllegalAccessException {
        ProducerRepository.showProducerMetaData();
    }
    public static void showDriverMetaData() throws IllegalAccessException {
        ProducerRepository.showDriverMetaData();
    }
    public static void showTypeScrollWorking() throws IllegalAccessException {
        ProducerRepository.showTypeScrollWorking();
    }
    private static void requireValidId(Integer id) throws IllegalAccessException {
        if (id == null || id <= 0){
            throw new IllegalAccessException("Invalid value for id");
        }
    }
}

package comp31.a2.model.repositories;

import java.util.List;

import org.springframework.data.repository.ListCrudRepository;

import comp31.a2.model.entities.Entity1;

/**
 * Entity1Repository
 */
public interface Entity1Repository extends ListCrudRepository<Entity1, Long> {
 List <Entity1> findByDescription(String description);
 List<Entity1> findAll();
}
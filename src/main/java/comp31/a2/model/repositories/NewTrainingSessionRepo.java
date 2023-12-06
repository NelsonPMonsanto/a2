package comp31.a2.model.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import comp31.a2.model.entities.NewTrainingSession;

public interface NewTrainingSessionRepo extends 
CrudRepository<NewTrainingSession,Integer> {
    
    public List<NewTrainingSession> findAll();
}

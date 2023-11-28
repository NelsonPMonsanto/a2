package comp31.a2.model.repositories;

import java.util.List;

import comp31.a2.model.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;

import comp31.a2.model.entities.Trainer;

public interface TrainerRepo extends CrudRepository<Trainer,Integer> {
    
    public List<Trainer> findAll();


}

package comp31.a2.model.repositories;

import java.util.List;

import comp31.a2.model.entities.Trainee;

import org.springframework.data.repository.CrudRepository;

public interface TraineeRepo extends CrudRepository<Trainee,Integer>{
    
    public List<Trainee> findAll();

    public Trainee findTraineeById(Integer id);
    
}

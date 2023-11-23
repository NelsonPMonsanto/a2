package comp31.a2.model.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import comp31.a2.model.entities.TrainingPlan;

public interface TrainingPlanRepo extends CrudRepository<TrainingPlan, Integer>{
    
    public List<TrainingPlan> findAll();
}

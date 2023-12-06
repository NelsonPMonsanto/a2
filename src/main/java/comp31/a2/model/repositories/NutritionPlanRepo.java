package comp31.a2.model.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import comp31.a2.model.entities.NutritionPlan;

public interface NutritionPlanRepo extends CrudRepository<NutritionPlan,Integer>{

    public List<NutritionPlan> findAll();
    
}

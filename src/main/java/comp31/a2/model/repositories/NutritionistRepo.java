package comp31.a2.model.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import comp31.a2.model.entities.Nutritionist;
// import comp31.a2.model.entities.Trainee;



public interface NutritionistRepo extends CrudRepository<Nutritionist,Integer>{

    public List<Nutritionist> findAll();


}

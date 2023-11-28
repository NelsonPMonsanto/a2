package comp31.a2.model.repositories;

import java.util.List;

import comp31.a2.model.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;

import comp31.a2.model.entities.Nutritionist;



public interface NutritionistRepo extends CrudRepository<Nutritionist,Integer>{

    public List<Nutritionist> findAll();

    /*public Nutritionist findByFirstName(String Name);*/

}

package comp31.a2.model.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import comp31.a2.model.entities.UserEntity;

public interface UserRepo extends CrudRepository<UserEntity,Integer>{
    
    public List<UserEntity> findAll();
}

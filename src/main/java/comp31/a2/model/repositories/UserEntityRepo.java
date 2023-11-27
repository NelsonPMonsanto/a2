package comp31.a2.model.repositories;

import java.util.List;


import org.springframework.data.repository.CrudRepository;

import comp31.a2.model.entities.UserEntity;

public interface UserEntityRepo extends CrudRepository<UserEntity,Integer>{
    
    public List<UserEntity> findAll();

    public UserEntity findByFirstName(String Name);

    public List<UserEntity> findByUserType(Integer type);

}

// SELECT * FROM USER_ENTITY 
// join TRAINEE 
// on TRE_USER_ID = USER_ENTITY.id
// left join TRAINING_PLAN 
// on FK_TRAINEE = TRAINEE .ID
// where FK_TRAINEE <> 0
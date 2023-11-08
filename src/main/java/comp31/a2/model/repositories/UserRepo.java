package comp31.a2.model.repositories;

import java.util.List;

import org.springframework.data.repository.ListCrudRepository;

import comp31.a2.model.entities.User;

public interface UserRepo extends ListCrudRepository<User, Long>{
    List <User> findAll();

}

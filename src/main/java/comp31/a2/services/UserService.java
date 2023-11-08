package comp31.a2.services;
import java.util.List;

import org.springframework.stereotype.Service;

import comp31.a2.model.entities.UserEntity;
import comp31.a2.model.repositories.UserRepo;

@Service
public class UserService {
    
    UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }
    
    public List<UserEntity> findAllUsers()
    {
       return userRepo.findAll();
    }

}

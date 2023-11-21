package comp31.a2.services;
import java.util.List;

import org.springframework.stereotype.Service;

import comp31.a2.model.entities.Nutritionist;
import comp31.a2.model.entities.Trainee;
import comp31.a2.model.entities.Trainer;
import comp31.a2.model.entities.UserEntity;
import comp31.a2.model.repositories.NutritionistRepo;
import comp31.a2.model.repositories.TraineeRepo;
import comp31.a2.model.repositories.TrainerRepo;
import comp31.a2.model.repositories.UserEntityRepo;

@Service
public class UserService {
    
    UserEntityRepo userRepo;
    TrainerRepo trainerRepo;
    TraineeRepo traineeRepo;
    NutritionistRepo nutritionistRepo;
    
    public UserService(TrainerRepo trainerRepo, TraineeRepo traineeRepo, NutritionistRepo nutritionistRepo,UserEntityRepo userRepo) {
        this.userRepo = userRepo;
        this.trainerRepo = trainerRepo;
        this.traineeRepo = traineeRepo;
        this.nutritionistRepo = nutritionistRepo;
    }
    public List<Nutritionist> findAllNutritionist()
    {
       return nutritionistRepo.findAll();
    }

    public List<Trainer> findAllTrainers()
    {
       return trainerRepo.findAll();
    }

    public List<Trainee> findAllTrainees()
    {
       return traineeRepo.findAll();
    }
    
    public List<UserEntity> findAllUsers()
    {
       return userRepo.findAll();
    }

    public List<UserEntity> findUsersByFirstName(String name)
    {
       return userRepo.findByFirstName(name);
    }
}

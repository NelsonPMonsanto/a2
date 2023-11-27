package comp31.a2.services;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import comp31.a2.model.entities.NewTrainingSession;
import comp31.a2.model.entities.Nutritionist;
import comp31.a2.model.entities.Trainee;
import comp31.a2.model.entities.Trainer;
import comp31.a2.model.entities.UserEntity;
import comp31.a2.model.repositories.NewTrainingSessionRepo;
import comp31.a2.model.repositories.NutritionistRepo;
import comp31.a2.model.repositories.TraineeRepo;
import comp31.a2.model.repositories.TrainerRepo;
import comp31.a2.model.repositories.UserEntityRepo;

@Service
public class UserService {
    
    NewTrainingSessionRepo newTrainingSessionRepo;
    UserEntityRepo userRepo;
    TrainerRepo trainerRepo;
    TraineeRepo traineeRepo;
    NutritionistRepo nutritionistRepo;
    
    public UserService(TrainerRepo trainerRepo, TraineeRepo traineeRepo, NutritionistRepo nutritionistRepo,UserEntityRepo userRepo, 
    NewTrainingSessionRepo newTrainingSessionRepo) {
        this.userRepo = userRepo;
        this.trainerRepo = trainerRepo;
        this.traineeRepo = traineeRepo;
        this.nutritionistRepo = nutritionistRepo;
        this.newTrainingSessionRepo = newTrainingSessionRepo;
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

    public List<Trainer> findAvailableTrainers() //NOV26
    {
      return trainerRepo.findByIsAvailableTrue();
    } 

    public UserEntity findUserById(Integer id)
   {
      List<UserEntity> users = userRepo.findAll();
      // ;
      for (UserEntity user : users) {
         System. out. println(user);
         if (user.getId() == id)
         {
            return user;
         }
      }
     return null;
   }

   public void saveNewTrainingSession(NewTrainingSession newTrainingSession) 
   {
      newTrainingSessionRepo.save(newTrainingSession);
   }
}

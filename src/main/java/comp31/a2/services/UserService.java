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

   public UserService(TrainerRepo trainerRepo, TraineeRepo traineeRepo, NutritionistRepo nutritionistRepo,
         UserEntityRepo userRepo) {
      this.userRepo = userRepo;
      this.trainerRepo = trainerRepo;
      this.traineeRepo = traineeRepo;
      this.nutritionistRepo = nutritionistRepo;
   }

   public List<Nutritionist> findAllNutritionist() {
      return nutritionistRepo.findAll();
   }
public Trainee findTraineeById(Integer id) {
    return traineeRepo.findTraineeById(id);
}
    public  List<Trainer> findAllTrainers()
    {
       return trainerRepo.findAll();
    }

    public List<Trainee> findAllTrainees()    {
       return traineeRepo.findAll();
    }
    
    public List<UserEntity> findAllUsers()
    {
       return userRepo.findAll();
    }

   public List<UserEntity> findUsersByFirstName(String name) {
      return userRepo.findByFirstName(name);
   }

   public List<Trainee> findTraineesByNutritionist(Nutritionist nutritionist) {
      return null;
   }

   public Trainer findTrainerWithLeastTrainees(List<Trainer> trainers) {
      Trainer trainerWithLeastTrainees = null;
      int minTrainees = Integer.MAX_VALUE;

      for (Trainer trainer : trainers) {
         int traineeCount = trainer.getTrainees().size();
         if (traineeCount < minTrainees) {
            minTrainees = traineeCount;
            trainerWithLeastTrainees = trainer;
         }
      }

      return trainerWithLeastTrainees;
   }
   public Nutritionist findNutritionistWithLeastClients(List<Nutritionist> nutritionists) {
      Nutritionist nutritionistWithLeastClients = null;
      int minClients = Integer.MAX_VALUE;

      for (Nutritionist nutritionist : nutritionists) {
          int clientCount = nutritionist.getTrainees().size();
          if (clientCount < minClients) {
              minClients = clientCount;
              nutritionistWithLeastClients = nutritionist;
          }
      }

      return nutritionistWithLeastClients;
  }


  public Nutritionist findNutritionistById(Integer Id) {

     List<Nutritionist> nutritionists = nutritionistRepo.findAll();
     Nutritionist newNutritionist=null;

     for (Nutritionist nutritionist : nutritionists) {
        if(nutritionist.getId() == Id){
           newNutritionist=nutritionist;
           break;
        }
     }
     return newNutritionist;
  }
   public Trainer findTrainerById(Integer Id) {
      List<Trainer> trainers = trainerRepo.findAll();

      Trainer newTrainer=null;
        for (Trainer trainer : trainers) {
             if(trainer.getId()==Id){
                newTrainer=trainer;
             }
        }
      return newTrainer;
   }
}

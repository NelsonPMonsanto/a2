package comp31.a2.services;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import comp31.a2.model.entities.NutritionPlan;
import comp31.a2.model.entities.NewTrainingSession;
import comp31.a2.model.entities.Nutritionist;
import comp31.a2.model.entities.Trainee;
import comp31.a2.model.entities.Trainer;
import comp31.a2.model.entities.TrainingPlan;
import comp31.a2.model.entities.UserEntity;
import comp31.a2.model.repositories.NutritionPlanRepo;
import comp31.a2.model.repositories.NewTrainingSessionRepo;
import comp31.a2.model.repositories.NutritionistRepo;
import comp31.a2.model.repositories.TraineeRepo;
import comp31.a2.model.repositories.TrainerRepo;
import comp31.a2.model.repositories.TrainingPlanRepo;
import comp31.a2.model.repositories.UserEntityRepo;

@Service
public class UserService {
    
   
   UserEntityRepo userRepo;
   TrainerRepo trainerRepo;
   TraineeRepo traineeRepo;
   NutritionistRepo nutritionistRepo;
   TrainingPlanRepo trainingPlanRepo;
   NutritionPlanRepo nutritionPlanRepo;
   NewTrainingSessionRepo newTrainingSessionRepo;

   public UserService(TrainerRepo trainerRepo, TraineeRepo traineeRepo, NutritionistRepo nutritionistRepo,UserEntityRepo userRepo, 
   TrainingPlanRepo trainingPlanRepo,NutritionPlanRepo nutritionPlanRepo,NewTrainingSessionRepo newTrainingSessionRepo) {
      this.userRepo = userRepo;
      this.trainerRepo = trainerRepo;
      this.traineeRepo = traineeRepo;
      this.nutritionistRepo = nutritionistRepo;
      this.trainingPlanRepo = trainingPlanRepo;
      this.nutritionPlanRepo = nutritionPlanRepo;
      this.newTrainingSessionRepo = newTrainingSessionRepo;
   }


   public List<Nutritionist> findAllNutritionist()
   {
      return nutritionistRepo.findAll();
   }

   public List<Trainee> getTraineesWithTrainingPLan(List<Trainee> trainees)
   {
      List<Trainee> newList = new ArrayList<Trainee>();
      for (Trainee trainee : trainees) {

         if(trainee.getTrainingPlan() != null)
         {
            newList.add(trainee);
         }    
      }
      return newList;
   }

   public List<Trainee> getTraineesWithoutTrainingPLan(List<Trainee> trainees)
   {
      List<Trainee> newList = new ArrayList<Trainee>();
      for (Trainee trainee : trainees) {

         if(trainee.getTrainingPlan() == null)
         {
            newList.add(trainee);
         }    
      }
      return newList;
   }

   public List<Trainer> findAllTrainers()
   {
      return trainerRepo.findAll();
   }

   public List<Trainee> getTraineesWithNutritionPLan(List<Trainee> trainees)
   {
      List<Trainee> newList = new ArrayList<Trainee>();
      for (Trainee trainee : trainees) {

         if(trainee.getNutritionPlan() != null)
         {
            newList.add(trainee);
         }    
      }
      return newList;
   }

   public List<Trainee> getTraineesWithoutNutritionPLan(List<Trainee> trainees)
   {
      List<Trainee> newList = new ArrayList<Trainee>();
      for (Trainee trainee : trainees) {

         if(trainee.getNutritionPlan() == null)
         {
            newList.add(trainee);
         }    
      }
      return newList;
   }

   public List<Trainee> findAllTrainees()
   {
      return traineeRepo.findAll();
   }

   public Trainee findTraineeById(Integer id)
   {
      List<Trainee> trainees = traineeRepo.findAll();
         
      for (Trainee trainee : trainees) {
         
         if (trainee.getId() == id)
         {
            return trainee;
         }
      }
     return null;
   }

   public List<UserEntity> findAllUsers()
   {
      return userRepo.findAll();
   }

   public List<UserEntity> findUsersByUserType(Integer type)
   {
      return userRepo.findByUserType(type);
   }

   public void addTrainingPlan(TrainingPlan trainingPlan)
   {
      trainingPlanRepo.save(trainingPlan);
   }

   public void addNutritionPlan(NutritionPlan nutritionPlan)
   {
      nutritionPlanRepo.save(nutritionPlan);
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

   public void saveUser( UserEntity userEntity)
   {
      userRepo.save(userEntity);
   }

   public void saveTrainer(Trainer trainer)
   {
      trainerRepo.save(trainer);
   }
   public void saveTrainee(Trainee trainee)
   {
      traineeRepo.save(trainee);
   }
   public void saveNutritionist(Nutritionist nutritionist)
   {
      nutritionistRepo.save(nutritionist);
   }

   public UserEntity findByUsername (String username)
   {
      return userRepo.findByUsername(username);
   }







}

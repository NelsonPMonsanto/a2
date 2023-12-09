package comp31.a2;

//import java.time.LocalDateTime;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

// import comp31.a2.model.entities.NutritionPlan;
import comp31.a2.model.entities.Nutritionist;
//import comp31.a2.model.entities.NewTrainingSession;
import comp31.a2.model.entities.Trainee;
import comp31.a2.model.entities.Trainer;
// import comp31.a2.model.entities.TrainingPlan;
import comp31.a2.model.entities.UserEntity;
import comp31.a2.model.repositories.NutritionPlanRepo;
//import comp31.a2.model.repositories.NewTrainingSessionRepo;
import comp31.a2.model.repositories.NutritionistRepo;
import comp31.a2.model.repositories.TraineeRepo;
import comp31.a2.model.repositories.TrainerRepo;
import comp31.a2.model.repositories.TrainingPlanRepo;
import comp31.a2.model.repositories.UserEntityRepo;

@Component
public class InitData implements CommandLineRunner {

    UserEntityRepo userRepo;
    TrainerRepo trainerRepo;
    NutritionistRepo nutritionistRepo;
    TraineeRepo traineeRepo;
    NutritionPlanRepo nutritionPlanRepo;
    TrainingPlanRepo trainingPlanRepo;

    public InitData(TrainerRepo trainerRepo, NutritionistRepo nutritionistRepo, TraineeRepo traineeRepo,UserEntityRepo userRepo,NutritionPlanRepo nutritionPlanRepo,TrainingPlanRepo trainingPlanRepo) {
        this.userRepo = userRepo;
        this.trainerRepo = trainerRepo;
        this.nutritionistRepo = nutritionistRepo;
        this.traineeRepo = traineeRepo;
        this.nutritionPlanRepo = nutritionPlanRepo;
        this.trainingPlanRepo = trainingPlanRepo;
    }

    @Override
    public void run(String... args) throws Exception {

        UserEntity userEntity2 = new UserEntity("pablo", "Pablo", "Roger", "qwer2", 0);
        UserEntity userEntity1 = new UserEntity("paul", "Paul", "Roger", "qwer1", 1);
        UserEntity userEntity5 = new UserEntity("lucas", "Lucas", "Smith", "qwer5", 1);
        UserEntity userEntity8 = new UserEntity("oliver", "Oliver", "Brown", "qwer8", 1);
        UserEntity userEntity11 = new UserEntity("isabella", "Isabella", "Lee", "qwer11", 1);
        UserEntity userEntity14 = new UserEntity("ethan", "Ethan", "Gonzalez", "qwer14", 1);
        userRepo.save(userEntity1);
        userRepo.save(userEntity5);
        userRepo.save(userEntity8);
        userRepo.save(userEntity11);
        userRepo.save(userEntity14);

        Trainer trainer1 = new Trainer(userEntity1, true);
        Trainer trainer2 = new Trainer(userEntity5, false);
        Trainer trainer3 = new Trainer(userEntity8, true);
        Trainer trainer4 = new Trainer(userEntity11, false);
        Trainer trainer5 = new Trainer(userEntity14, true);
        trainerRepo.save(trainer1);
        trainerRepo.save(trainer2);
        trainerRepo.save(trainer3);
        trainerRepo.save(trainer4);
        trainerRepo.save(trainer5);

        UserEntity userEntity3 = new UserEntity("raul", "Saul", "Danmation", "qwer3", 2);
        UserEntity userEntity6 = new UserEntity("mia", "Mia", "Johnson", "qwer6", 2);
        UserEntity userEntity9 = new UserEntity("ava", "Ava", "Martinez", "qwer9", 2);
        UserEntity userEntity12 = new UserEntity("liam", "Liam", "Lopez", "qwer12", 2);
        UserEntity userEntity15 = new UserEntity("mia", "Mia", "Kim", "qwer15", 2);
        userRepo.save(userEntity3);
        userRepo.save(userEntity6);
        userRepo.save(userEntity9);
        userRepo.save(userEntity12);
        userRepo.save(userEntity15);

        Nutritionist nutritionist1 = new Nutritionist(userEntity3);
        Nutritionist nutritionist2 = new Nutritionist(userEntity6);
        Nutritionist nutritionist3 = new Nutritionist(userEntity9);
        Nutritionist nutritionist4 = new Nutritionist(userEntity12);
        Nutritionist nutritionist5 = new Nutritionist(userEntity15);
        nutritionistRepo.save(nutritionist1);
        nutritionistRepo.save(nutritionist2);
        nutritionistRepo.save(nutritionist3);
        nutritionistRepo.save(nutritionist4);
        nutritionistRepo.save(nutritionist5);

        UserEntity userEntity2 = new UserEntity("pablo", "Pablo", "Roger", "qwer2", 0);
        UserEntity userEntity4 = new UserEntity("emma", "Emma", "Thompson", "qwer4", 0);
        UserEntity userEntity7 = new UserEntity("sophie", "Sophie", "Williams", "qwer7", 0);
        UserEntity userEntity10 = new UserEntity("noah", "Noah", "Garcia", "qwer10", 0);
        UserEntity userEntity13 = new UserEntity("amelia", "Amelia", "Nguyen", "qwer13", 0);
        UserEntity userEntity16 = new UserEntity("logan", "Logan", "Patel", "qwer16", 0);
        UserEntity userEntity17 = new UserEntity("hannah", "Hannah", "Wilson", "qwer17", 0);

        userRepo.save(userEntity2);
        userRepo.save(userEntity4);
        userRepo.save(userEntity7);
        userRepo.save(userEntity10);
        userRepo.save(userEntity13);
        userRepo.save(userEntity16);
        userRepo.save(userEntity17); 
        // REMEMBER USERENTITY17 HAS NULL VALUES IT DOES NOT WORK!!! Keep it commented until workaround is build

        Trainee trainee1 = new Trainee(nutritionist1,trainer1,userEntity2);
        Trainee trainee2 = new Trainee(nutritionist2,trainer2,userEntity4);
        Trainee trainee3 = new Trainee(nutritionist3,trainer3,userEntity7);
        Trainee trainee4 = new Trainee(nutritionist3,trainer4,userEntity10);
        Trainee trainee5 = new Trainee(nutritionist4,trainer3,userEntity13);
        Trainee trainee6 = new Trainee(nutritionist3,trainer3,userEntity16);
        Trainee trainee7 = new Trainee(null,null,userEntity17);

        traineeRepo.save(trainee1);
        traineeRepo.save(trainee2);  
        traineeRepo.save(trainee3);  
        traineeRepo.save(trainee4);      
        traineeRepo.save(trainee5);  
        traineeRepo.save(trainee6);  
        traineeRepo.save(trainee7);  

        // NutritionPlan nutritionPlan1 = new NutritionPlan("Lose weight", "Carbohydrates", "Protein",10,20, trainee1);
        // nutritionPlanRepo.save(nutritionPlan1);

        // TrainingPlan trainingPlan1 = new TrainingPlan("Get muscle",  "Chest",  "squats", 30, 50, trainee1);
        // trainingPlanRepo.save(trainingPlan1);
    }
}

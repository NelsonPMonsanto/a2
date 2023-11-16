package comp31.a2;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import comp31.a2.model.entities.Trainee;
import comp31.a2.model.entities.Trainer;
import comp31.a2.model.entities.UserEntity;
import comp31.a2.model.repositories.NutritionistRepo;
import comp31.a2.model.repositories.TraineeRepo;
import comp31.a2.model.repositories.TrainerRepo;
import comp31.a2.model.repositories.UserEntityRepo;


@Component
public class InitData implements CommandLineRunner {

    UserEntityRepo userRepo;
    TrainerRepo trainerRepo;
    NutritionistRepo nutritionistRepo;
    TraineeRepo traineeRepo;

    public InitData(TrainerRepo trainerRepo, NutritionistRepo nutritionistRepo, TraineeRepo traineeRepo,UserEntityRepo userRepo) {
        this.userRepo = userRepo;
        this.trainerRepo = trainerRepo;
        this.nutritionistRepo = nutritionistRepo;
        this.traineeRepo = traineeRepo;
    }

    @Override
    public void run(String... args) throws Exception {

        UserEntity userEntity1 = new UserEntity("paul", "Paul", "Roger", "qwer1", true, false, false);
        // UserEntity userEntity1 = new UserEntity("paul", "Paul", "Roger", "qwer1", false,null, false,null, true,null);
        userRepo.save(userEntity1);

        UserEntity userEntity2 = new UserEntity("pablo", "Pablo", "Roger", "qwer2", false, true, false);
        userRepo.save(userEntity2);

        Trainer trainer1 = new Trainer(userEntity1);
        trainerRepo.save(trainer1);

        Trainee trainee = new Trainee(null,trainer1,userEntity2);
        traineeRepo.save(trainee);    

        // traineeRepo.save(new Trainee("felix","Felix","Roger","qwer2",null,trainer1));


    }

}

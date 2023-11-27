package comp31.a2;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import comp31.a2.model.entities.Nutritionist;
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

        //0-Trainee 1-Trainer 2-Nutrtitionist
        //trainees
        UserEntity userEntity2 = new UserEntity("pablo", "Pablo", "Roger", "qwer2", 0);
        UserEntity userEntity5 = new UserEntity("alice", "Alice", "Smith", "qwer5", 0);
        UserEntity userEntity7 = new UserEntity("emma", "Emma", "Johnson", "qwer7", 0);
        UserEntity userEntity12 = new UserEntity("william", "William", "Jones", "qwer12", 0);
        UserEntity userEntity13 = new UserEntity("ava", "Ava", "Miller", "qwer13", 0);
        UserEntity userEntity16 = new UserEntity("ethan", "Ethan", "Lopez", "qwer16", 0);
        // UserEntity userEntity17 = new UserEntity("nick", "Nick", "Elio", "qwer17", 0);
        //trainers
        UserEntity userEntity1 = new UserEntity("paul", "Paul", "Roger", "qwer1", 1);
        UserEntity userEntity4 = new UserEntity("john", "John", "Doe", "qwer4", 1);
        UserEntity userEntity8 = new UserEntity("michael", "Michael", "Brown", "qwer8", 1);
        UserEntity userEntity11 = new UserEntity("olivia", "Olivia", "Garcia", "qwer11", 1);
        UserEntity userEntity14 = new UserEntity("noah", "Noah", "Davis", "qwer14", 1);
        //nutrionalist
        UserEntity userEntity3 = new UserEntity("raul", "Saul", "Danmation", "qwer3", 2);
        UserEntity userEntity6 = new UserEntity("bob", "Bob", "Johnson", "qwer6", 2);
        UserEntity userEntity9 = new UserEntity("sophia", "Sophia", "Williams", "qwer9", 2);
        UserEntity userEntity10 = new UserEntity("jacob", "Jacob", "Martinez", "qwer10", 2);
        UserEntity userEntity15 = new UserEntity("isabella", "Isabella", "Rodriguez", "qwer15", 2);

        userRepo.save(userEntity1);
        userRepo.save(userEntity2);
        userRepo.save(userEntity3);
        userRepo.save(userEntity4);
        userRepo.save(userEntity5);
        userRepo.save(userEntity6);
        userRepo.save(userEntity7);
        userRepo.save(userEntity8);
        userRepo.save(userEntity9);
        userRepo.save(userEntity10);
        userRepo.save(userEntity11);
        userRepo.save(userEntity12);
        userRepo.save(userEntity13);
        userRepo.save(userEntity14);
        userRepo.save(userEntity15);
        userRepo.save(userEntity16);

        //Trainer
        Trainer trainer1 = new Trainer(userEntity1);
        trainerRepo.save(trainer1);
        Trainer trainer2 = new Trainer(userEntity4);
        trainerRepo.save(trainer2);
        Trainer trainer3 = new Trainer(userEntity8);
        trainerRepo.save(trainer3);
        Trainer trainer4 = new Trainer(userEntity11);
        trainerRepo.save(trainer4);
        Trainer trainer5 = new Trainer(userEntity14);
        trainerRepo.save(trainer5);

        //Nutritionist
        Nutritionist nutritionist1 = new Nutritionist(userEntity3);
        nutritionistRepo.save(nutritionist1);
        Nutritionist nutritionist2 = new Nutritionist(userEntity6);
        nutritionistRepo.save(nutritionist2);
        Nutritionist nutritionist3 = new Nutritionist(userEntity9);
        nutritionistRepo.save(nutritionist3);
        Nutritionist nutritionist4 = new Nutritionist(userEntity10);
        nutritionistRepo.save(nutritionist4);
        Nutritionist nutritionist5 = new Nutritionist(userEntity15);
        nutritionistRepo.save(nutritionist5);


        //Trainees
        Trainee trainee1 = new Trainee(nutritionist1,trainer1,userEntity2);
        traineeRepo.save(trainee1);    
        Trainee trainee2 = new Trainee(nutritionist2,trainer2,userEntity5);
        traineeRepo.save(trainee2);    
        Trainee trainee3 = new Trainee(nutritionist3,trainer3,userEntity7);
        traineeRepo.save(trainee3);    
        Trainee trainee4 = new Trainee(nutritionist4,trainer4,userEntity12);
        traineeRepo.save(trainee4);    
        Trainee trainee5 = new Trainee(nutritionist5,trainer5,userEntity13);
        traineeRepo.save(trainee5);
        Trainee trainee6 = new Trainee(nutritionist5,trainer5,userEntity16);
        traineeRepo.save(trainee6);
        // Trainee trainee7 = new Trainee(null,null,userEntity17);
        // traineeRepo.save(trainee7);
        
        // UserEntity userEntity17 = new UserEntity("nick", "Nick", "Elio", "qwer17", 0);
        // userRepo.save(userEntity17);
        // Trainee trainee7 = new Trainee(null, null, userEntity17);
        // traineeRepo.save(trainee7);






        // traineeRepo.save(new Trainee("felix","Felix","Roger","qwer2",null,trainer1));



    }

}

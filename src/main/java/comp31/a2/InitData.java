package comp31.a2;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import comp31.a2.model.entities.UserEntity;
import comp31.a2.model.repositories.UserRepo;

@Component
public class InitData implements CommandLineRunner {

    UserRepo userRepo;

    public InitData(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public void run(String... args) throws Exception {

        userRepo.save(new UserEntity("paul","Paul","Fenix","qwer1","trainer"));

    }

}

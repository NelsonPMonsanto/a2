package comp31.a2;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import comp31.a2.model.entities.Department;
import comp31.a2.model.entities.Entity1;
import comp31.a2.model.entities.User;
import comp31.a2.model.repositories.DepartmentRepo;
import comp31.a2.model.repositories.Entity1Repository;
import comp31.a2.model.repositories.UserRepo;

@Component
public class InitData implements CommandLineRunner {

    Entity1Repository entity1Repository;
    DepartmentRepo departmentRepo;
    UserRepo userRepo;

    public InitData(Entity1Repository entity1Repository, DepartmentRepo departmentRepo, UserRepo userRepo) {
        this.entity1Repository = entity1Repository;
        this.departmentRepo = departmentRepo;
        this.userRepo = userRepo;
    }

    @Override
    public void run(String... args) throws Exception {

        Department trainer = new Department("Trainer");
        Department trainee = new Department("Trainee");
        Department nutritionist = new Department("Nutritionist");

        departmentRepo.save(trainer);
        departmentRepo.save(trainee);
        departmentRepo.save(nutritionist);

        System.out.println("---- Created Departments");

        userRepo.save(new User("John", "Doe", trainee));
        userRepo.save(new User("Jane", "Doe", trainer));
        userRepo.save(new User("John", "Smith", nutritionist));
        

        System.out.println("---- Created Users");
    }

}

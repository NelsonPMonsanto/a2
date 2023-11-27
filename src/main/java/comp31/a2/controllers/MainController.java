package comp31.a2.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import comp31.a2.model.entities.Nutritionist;
import comp31.a2.model.entities.Trainee;
import comp31.a2.model.entities.Trainer;
import comp31.a2.model.entities.UserEntity;
import comp31.a2.model.repositories.TraineeRepo;
import comp31.a2.model.repositories.UserEntityRepo;
import comp31.a2.services.UserService;

import java.util.List;
import org.springframework.ui.Model;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;

@Controller
public class MainController {

    UserService userService;
    TraineeRepo traineeRepo;
    UserEntityRepo userRepo;
    // Logger logger = LoggerFactory.getLogger(MainController.class);
    

    public MainController(UserService userService, TraineeRepo traineeRepo, UserEntityRepo userRepo) {
        this.userService = userService;
        this.traineeRepo = traineeRepo;
        this.userRepo = userRepo;
    }

    @GetMapping("/")
    public String getRoot() {
        return "index";
    }

    @GetMapping("/usecase1")
    public String showAllUsers(Model model) {

        List<UserEntity> users = userService.findAllUsers();
        // List<UserEntity> users = userService.findUsersByFirstName("Pablo");
        // logger.info("here", users.size());
        model.addAttribute("users", users);
        List<Trainer> trainers = userService.findAllTrainers();
        model.addAttribute("trainers", trainers);
        List<Nutritionist> nutritionists = userService.findAllNutritionist();
        model.addAttribute("nutritionists", nutritionists);

        return "showAllUsers";
    }

    @GetMapping("/usecase2")
    public String showAllTrainers(Model model) {

        List<Trainer> trainers = userService.findAllTrainers();
        model.addAttribute("trainers", trainers);

        return "showAllTrainers";
    }

    @GetMapping("/usecase3")
    public String showAllNutritionist(Model model) {

        List<Nutritionist> nutritionists = userService.findAllNutritionist();
        model.addAttribute("nutritionists", nutritionists);

        return "showAllNutritionist";
    }

    @GetMapping("/usecase4")
    public String showAllTrainees(Model model) {

        List<Trainee> trainees = userService.findAllTrainees();
        model.addAttribute("trainees", trainees);

        return "showAllTrainees";
    }

    @GetMapping("/usecase5")
    public String findUsersByFirstName(Model model) {

        List<UserEntity> users = userService.findUsersByFirstName("Paul");
        // logger.info("here", users.size());
        model.addAttribute("users", users);

        return "findUsersByFirstName";
    }

    @GetMapping({"/usecase6"})
    public String createAccount(Model model) {
        Trainee trainee = traineeRepo.findTraineeById(7);
        model.addAttribute("trainee", trainee);
        UserEntity userEntity17 = new UserEntity("nick", "Nick", "Elio", "qwer17", 0);
        userRepo.save(userEntity17);
        Trainee trainee7 = new Trainee(null, null, userEntity17);
        traineeRepo.save(trainee7);
        model.addAttribute("trainee", trainee7);
        return "createAccount";
    }


    @PostMapping("/addMentors")
    public String assignMentors(Model model) {
        List<Trainer> trainers = userService.findAllTrainers();
        List<Nutritionist> nutritionist = userService.findAllNutritionist();
        Nutritionist nutritionistWLeastClients = userService.findNutritionistWithLeastClients(nutritionist);
        Trainer trainerWLeastClients = userService.findTrainerWithLeastTrainees(trainers);

        Trainee trainee = traineeRepo.findTraineeById(7);
        trainee.setNutritionist(nutritionistWLeastClients);
        trainee.setTrainer(trainerWLeastClients);
        traineeRepo.save(trainee);

        return "redirect:/createAccount";
    }
    @GetMapping("/createAccount")
    public String assignedMentors(Model model)
    {
        Trainee trainee = traineeRepo.findTraineeById(7);
        model.addAttribute("trainee", trainee);
        return "createAccount";
    }

}

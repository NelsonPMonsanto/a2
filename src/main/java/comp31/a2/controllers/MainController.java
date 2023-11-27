package comp31.a2.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import comp31.a2.model.entities.Nutritionist;
import comp31.a2.model.entities.Trainee;
import comp31.a2.model.entities.Trainer;
import comp31.a2.model.entities.UserEntity;
import comp31.a2.services.UserService;

import java.util.List;
import org.springframework.ui.Model;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;

@Controller
public class MainController {
<<<<<<< HEAD
UserService userService;
    @GetMapping("/uc1")
    public String getUseCase1(Model model) {

        List<Trainer> trainers = userService.findAllTrainers();
        model.addAttribute("trainers", trainers);

        List<Trainee> trainees = userService.findAllTrainees();
        model.addAttribute("trainees", trainees);

        List<UserEntity> users = userService.findAllUsers();
=======

    UserService userService;
    // Logger logger = LoggerFactory.getLogger(MainController.class);

    public MainController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String getRoot()
    {
        return "index";
    }

    @GetMapping("/usecase1")
    public String showAllUsers(Model model) {

        List<UserEntity> users = userService.findAllUsers();
        // List<UserEntity> users = userService.findUsersByFirstName("Pablo");
>>>>>>> 5b2e5fff52a6cdbb2fcf854d8494235879ec8fb0
        // logger.info("here", users.size());
        model.addAttribute("users", users);
        
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
}

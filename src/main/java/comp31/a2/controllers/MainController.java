package comp31.a2.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import comp31.a2.model.entities.NewTrainingSession;
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

     
    @GetMapping("/startNewTrainingSession")
    public String startNewTrainingSession(Model model) {
    // Fetch the UserEntity object for the current user
    UserEntity user = userService.getCurrentUser();

    // Create a new NewTrainingSession object and link it to the user
    NewTrainingSession newTrainingSession = new NewTrainingSession();
    newTrainingSession.setUser(user);

    // Find an available trainer
    Trainer trainer = userService.findAvailableTrainers().get(0); // This is just an example. You should add error handling here.

    // Link the trainer to the new training session
    newTrainingSession.setTrainer(trainer);

    model.addAttribute("newTrainingSession", newTrainingSession);
    model.addAttribute("user", user);
    model.addAttribute("trainer", trainer);

    return "startNewTrainingSession";
    }

    @PostMapping("/startNewTrainingSession")
    public String handleNewTrainingSession( Model model) {    
    // Handle the new training session here
    // For example, you could save the new training session to the database

    // Then return the name of the Thymeleaf template that should be displayed next
    return "startNewTrainingSession";
}
    

}

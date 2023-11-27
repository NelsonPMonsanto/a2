package comp31.a2.controllers;

import org.slf4j.LoggerFactory;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class MainController {

    UserService userService;
    Logger logger = LoggerFactory.getLogger(MainController.class);

    
    public MainController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/uc1")
    public String getUseCase1(Model model) {

        List<Trainer> trainers = userService.findAllTrainers();
        model.addAttribute("trainers", trainers);

        List<Trainee> trainees = userService.findAllTrainees();
        model.addAttribute("trainees", trainees);

        List<UserEntity> users = userService.findAllUsers();
        // logger.info("here", users.size());
        model.addAttribute("users", users);
        
        return "usecase1";
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

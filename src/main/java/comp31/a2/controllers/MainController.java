package comp31.a2.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import comp31.a2.model.entities.Nutritionist;
import comp31.a2.model.entities.Trainee;
import comp31.a2.model.entities.Trainer;
import comp31.a2.model.entities.UserEntity;
import comp31.a2.services.UserService;

import java.util.List;
import org.springframework.ui.Model;

@Controller
public class MainController {

    UserService userService;

    public MainController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String getRoot() {
        return "index";
    }

    @GetMapping({"/usercase1","/showUsers"})
    public String showAllUsers(Model model) {
        List<UserEntity> users = userService.findAllUsers();
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
        model.addAttribute("users", users);
        return "findUsersByFirstName";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("userEntity", new UserEntity());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(UserEntity userEntity) {
        userService.saveUser(userEntity);
        List<UserEntity> allUsers = userService.findAllUsers();
        UserEntity savedUser = allUsers.get(allUsers.size()-1);
        switch(userEntity.getUserType())
        {
            case 0 : 
            Trainee newtrainee = new Trainee(null , null, savedUser);
            userService.saveTrainee(newtrainee);
            break;
            case 1 : 
            Trainer newtrainer = new Trainer(savedUser);
            userService.saveTrainer(newtrainer);
            break;
            default:
            Nutritionist newnutritionist = new Nutritionist(savedUser);
            userService.saveNutritionist(newnutritionist);
            break;
        }
        return "redirect:/showUsers";
    }

    @GetMapping("/user-details")
    public String showUserDetails(Model model) {
        List<UserEntity> users = userService.findUsersByFirstName("Paul");
        model.addAttribute("users", users);
        return "findUsersByFirstName";
    }
}

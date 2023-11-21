package comp31.a2.controllers;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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

    @GetMapping("/uc1")
    public String getUseCase1(Model model) {

        List<Trainer> trainers = UserService.findAllTrainers();
        model.addAttribute("trainers", trainers);

        List<Trainee> trainees = UserService.findAllTrainees();
        model.addAttribute("trainees", trainees);

        List<UserEntity> users = UserService.findAllUsers();
        // logger.info("here", users.size());
        model.addAttribute("users", users);
        
        return "usecase1";
    }
}

package comp31.a2.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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

    @GetMapping("/uc1")
    public String getUseCase1(Model model) {

        List<UserEntity> users = userService.findAllUsers();
        model.addAttribute("users", users);

        return "usecase1";
    }



}

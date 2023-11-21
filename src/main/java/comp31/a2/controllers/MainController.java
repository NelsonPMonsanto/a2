package comp31.a2.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import comp31.a2.model.entities.Department;
import comp31.a2.model.entities.User;
import comp31.a2.services.UserService;

@Controller
public class MainController {
    UserService userService;
    public MainController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/")
    public String getRoot(Model model) {
        
        List <User> users = userService.getAllUsers();
        model.addAttribute("employees", users);

        List<Department> departments = userService.getAllDepartments();
        model.addAttribute("departments", departments);
        return "usecase1";
    }
}

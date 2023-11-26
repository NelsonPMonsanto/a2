package comp31.a2.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import comp31.a2.model.entities.NutritionPlan;
import comp31.a2.model.entities.Nutritionist;
import comp31.a2.model.entities.Trainee;
import comp31.a2.model.entities.Trainer;
import comp31.a2.model.entities.TrainingPlan;
import comp31.a2.model.entities.UserEntity;
import comp31.a2.services.UserService;

import java.util.List;
import java.util.Random;

import org.springframework.ui.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class MainController {

    UserService userService;
    Logger logger = LoggerFactory.getLogger(MainController.class);
    UserEntity currentUser;

    public MainController(UserService userService) {
        this.userService = userService;
    }
    // Nutrition page
    @GetMapping("/")
    public String getRoot(Model model)
    {
        if(currentUser == null)
        {
            List<UserEntity> nutritionistList = userService.findUsersByUserType(2);
            Integer listrange = nutritionistList.size();
            Random rand = new Random(); 
            Integer upperbound = listrange;
            int int_random = rand.nextInt(upperbound);
            currentUser = nutritionistList.get(2);
        }
        model.addAttribute("currentuser", currentUser);
        return "nutritionistFunctions/nutritionistpage";
    }

    // Training page
    // @GetMapping("/")
    // public String getRoot(Model model)
    // {
    //     if(currentUser == null)
    //     {
    //         List<UserEntity> listoftrainer = userService.findUsersByUserType(1);
    //         Integer listrange = listoftrainer.size();
    //         Random rand = new Random(); 
    //         Integer upperbound = listrange;
    //         int int_random = rand.nextInt(upperbound);
    //         currentUser = listoftrainer.get(int_random);
    //     }
    //     model.addAttribute("currentuser", currentUser);
    //     return "trainerFunctions/trainerpage";
    // }

    @GetMapping("/createnutritionplan")
    public String createNutritionPlan(Model model)
    {
        Nutritionist nutritionist = currentUser.getNutritionist();  
        List<Trainee> traineeList = nutritionist.getTrainees();
        List<Trainee> traineesWithoutNutritionPLan = null;
        NutritionPlan nutritionPlan = new NutritionPlan();
        if(traineeList.size() > 0)
        {
            traineesWithoutNutritionPLan=userService.getTraineesWithoutTrainingPLan(traineeList);
        }
        model.addAttribute("noPlanTrainees", traineesWithoutNutritionPLan);
        model.addAttribute("currentuser", currentUser);
        model.addAttribute("newNutritionplan", nutritionPlan);
        return "nutritionistFunctions/createnutritionplan";
    }

    @PostMapping("/add-nutrition-plan")
    public String addNutritionPlan(NutritionPlan nutritionPlan, Model model)
    {   
        Trainee trainee = userService.findTraineeById(nutritionPlan.getTraineeId());  
        nutritionPlan.setTrainee_nutri_plan(trainee);
        nutritionPlan.setCaloriesIntake(0);
        nutritionPlan.setLitersOfWaterTaken(0);
        userService.addNutritionPlan(nutritionPlan);
        model.addAttribute("currentuser", currentUser);
        return "nutritionistFunctions/createnutritionplan";
    }


    @GetMapping("/createtrainingplan")
    public String createTrainingPlan(Model model)
    {
        Trainer trainer = currentUser.getTrainer();  
        List<Trainee> traineeList = trainer.getTrainees();
        List<Trainee> traineesWithoutTrainingPLan = null;
        TrainingPlan trainingPlan = new TrainingPlan();
        if(traineeList.size() > 0)
        {
            traineesWithoutTrainingPLan=userService.getTraineesWithoutTrainingPLan(traineeList);
        }
        model.addAttribute("noPlanTrainees", traineesWithoutTrainingPLan);
        model.addAttribute("currentuser", currentUser);
        model.addAttribute("newtrainingplan", trainingPlan);
        return "trainerFunctions/createtrainingplan";
    }

    @PostMapping("/add-training-plan")
    public String addTrainingPlan(TrainingPlan trainingPlan, Model model)
    {   
        Trainee trainee = userService.findTraineeById(trainingPlan.getTraineeId());  
        trainingPlan.setTrainee_train_plan(trainee);
        trainingPlan.setTrainingHours(0);
        trainingPlan.setWeight(0);
        userService.addTrainingPlan(trainingPlan);
        model.addAttribute("currentuser", currentUser);
        return "trainerFunctions/trainerpage";
    }

    @GetMapping("/usecase1")
    public String showAllUsers(Model model) {

        List<UserEntity> users = userService.findAllUsers();
        // List<UserEntity> users = userService.findUsersByFirstName("Pablo");

        // List<UserEntity> users = userService.findUsersByUserType(0);


        model.addAttribute("users", users);
        
        return "examples/showAllUsers";
    }

    @GetMapping("/usecase2")
    public String showAllTrainers(Model model) {

        List<Trainer> trainers = userService.findAllTrainers();
        model.addAttribute("trainers", trainers);
        
        return "examples/showAllTrainers";
    }
    
    @GetMapping("/usecase3")
    public String showAllNutritionist(Model model) {

        List<Nutritionist> nutritionists = userService.findAllNutritionist();
        model.addAttribute("nutritionists", nutritionists);
        return "examples/showAllNutritionist";
    }

    @GetMapping("/usecase4")
    public String showAllTrainees(Model model) {

        List<Trainee> trainees = userService.findAllTrainees();
        model.addAttribute("trainees", trainees);
        
        return "examples/showAllTrainees";
    }

    @GetMapping("/usecase5")
    public String findUsersByFirstName(Model model) {

        List<UserEntity> users = userService.findUsersByFirstName("Paul");

        model.addAttribute("users", users);
        return "examples/findUsersByFirstName";
    }




}

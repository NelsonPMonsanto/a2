package comp31.a2.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import comp31.a2.model.entities.NutritionPlan;
import comp31.a2.model.entities.Nutritionist;
import comp31.a2.model.entities.Trainee;
import comp31.a2.model.entities.Trainer;
import comp31.a2.model.entities.TrainingPlan;
import comp31.a2.model.entities.UserEntity;
import comp31.a2.services.UserService;

import java.util.ArrayList;
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

    // @GetMapping("/")
    // public String getRoot()
    // {
    //     return "index";
    // }

    // Trainee page
    @GetMapping("/")
    public String getRoot(Model model)
    {
        if(currentUser == null)
        {
            List<UserEntity> traineeList = userService.findUsersByUserType(0);
            Integer listrange = traineeList.size();
            Random rand = new Random(); 
            Integer upperbound = listrange;
            int int_random = rand.nextInt(upperbound);
            currentUser = traineeList.get(int_random);
        }
        model.addAttribute("currentuser", currentUser);
        return "traineepage";
    }

    // Nutrition page
    // @GetMapping("/")
    // public String getRoot(Model model)
    // {
    //     if(currentUser == null)
    //     {
    //         List<UserEntity> nutritionistList = userService.findUsersByUserType(2);
    //         Integer listrange = nutritionistList.size();
    //         Random rand = new Random(); 
    //         Integer upperbound = listrange;
    //         int int_random = rand.nextInt(upperbound);
    //         currentUser = nutritionistList.get(2);
    //     }
    //     model.addAttribute("currentuser", currentUser);
    //     return "nutritionistFunctions/nutritionistpage";
    // }

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
    //         currentUser = listoftrainer.get(2);
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
        return "nutritionistFunctions/nutritionistpage";
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

    @GetMapping("/checkstatsoftrainee")
    public String checktraineestats(Model model)
    {  
        String returnPage = "checkpersonalstats";
        String pageTitle = "Trainee";
        Integer userType = currentUser.getUserType();
        Trainer trainer = new Trainer();
        Nutritionist nutritionist = new Nutritionist();
        List<Trainee> traineeList = new ArrayList<Trainee>();
        boolean show = false;

        switch (userType) {
            case 1:
                trainer = currentUser.getTrainer();
                traineeList = trainer.getTrainees();
                model.addAttribute("traineeList",traineeList);
                returnPage = "showtraineesstats";
                pageTitle = "Trainer";
                break;
            case 2:
                nutritionist = currentUser.getNutritionist();
                traineeList = nutritionist.getTrainees();
                model.addAttribute("traineeList",traineeList);
                returnPage = "showtraineesstats";
                pageTitle = "Nutritionist";
                break;   
            default:
                pageTitle = "Trainee";
                show = true;
            break;
        }
        model.addAttribute("show", show);
        model.addAttribute("currentuser", currentUser);
        model.addAttribute("title", pageTitle);

        return returnPage;
    }

    @GetMapping("/showstats")
    public String showTraineeStats(Model model, @RequestParam Integer chosenTrainee) {
        String pageTitle = "";
        Integer userType = currentUser.getUserType();
        Trainer trainer = new Trainer();
        Nutritionist nutritionist = new Nutritionist();
        List<Trainee> traineeList = new ArrayList<Trainee>();
        Trainee showTrainee = userService.findTraineeById(chosenTrainee);
        switch (userType) {
            case 1:
                trainer = currentUser.getTrainer();
                traineeList = trainer.getTrainees();
                model.addAttribute("traineeList",traineeList);
                pageTitle = "Trainer";
                break;
            case 2:
                nutritionist = currentUser.getNutritionist();
                traineeList = nutritionist.getTrainees();
                model.addAttribute("traineeList",traineeList);
                pageTitle = "Nutritionist";
                break; 
            default:
                pageTitle = "Trainee";
            break;  
        }
        model.addAttribute("showTrainee", showTrainee);
        model.addAttribute("show", true);
        model.addAttribute("currentuser", currentUser);
        model.addAttribute("title", pageTitle);
        return "showtraineesstats";
    }

    @GetMapping("/usecase1")
    public String showAllUsers(Model model) {
        List<UserEntity> users = userService.findAllUsers();
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

        UserEntity users = userService.findByFirstName("Paul");

        model.addAttribute("users", users);
        return "examples/findUsersByFirstName";
    }




}

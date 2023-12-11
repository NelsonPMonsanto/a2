package comp31.a2.controllers;

import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import comp31.a2.model.entities.NutritionPlan;
import comp31.a2.model.entities.NewTrainingSession;
import comp31.a2.model.entities.Nutritionist;
import comp31.a2.model.entities.Trainee;
import comp31.a2.model.entities.Trainer;
import comp31.a2.model.entities.TrainingPlan;
import comp31.a2.model.entities.UserEntity;
import comp31.a2.services.UserService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;

@Controller
public class MainController {

    UserService userService;
    UserEntity currentUser;

    public MainController(UserService userService) {
        this.userService = userService;
    }
    //Build by Nick Elioppulos
    @GetMapping({"/", "/loginretry"})
    String getRoot(Model model) {
        UserEntity loginUser = new UserEntity();
        model.addAttribute("loginUser", loginUser);
        return "login-form";
    }

    //getForm
    // Validates the Log In form data. If data is valid User can Log in if not returns to log in page
    //Build by Nick Elioppulos
    @PostMapping("/login")
    public String getForm(UserEntity theUser, Model model) {
        UserEntity loginUser = userService.findByUsername(theUser.getUsername());
        String returnPage;
        if (loginUser == null || !loginUser.getPassword().equals(theUser.getPassword())) {
            UserEntity secondTryUser = new UserEntity();
            model.addAttribute("loginUser", secondTryUser);
            returnPage = "redirect:/loginretry";
        } else {
            currentUser= loginUser;
            switch (loginUser.getUserType()) {
                case 0:
                    returnPage = "traineepage";
                    break;
                case 1:
                    returnPage = "trainerFunctions/trainerpage";
                    break;
                default:
                    returnPage = "nutritionistFunctions/nutritionistpage";
                    break;
            }
            model.addAttribute("currentuser", currentUser);
        }
        return returnPage;
    }
    //createNutritionPlan
    // Shows user(Nutritionist) to create nutrition plan form
    // Made by Nelson Perez
    @GetMapping("/createnutritionplan")
    public String createNutritionPlan(Model model) {
        Nutritionist nutritionist = currentUser.getNutritionist();
        List<Trainee> traineeList = nutritionist.getTrainees();
        List<Trainee> traineesWithoutNutritionPLan = null;
        NutritionPlan nutritionPlan = new NutritionPlan();
        if (traineeList.size() > 0) {
            traineesWithoutNutritionPLan = userService.getTraineesWithoutTrainingPLan(traineeList);
        }
        model.addAttribute("noPlanTrainees", traineesWithoutNutritionPLan);
        model.addAttribute("currentuser", currentUser);
        model.addAttribute("newNutritionplan", nutritionPlan);
        return "nutritionistFunctions/createnutritionplan";
    }

    // addNutritionPlan
    // Create Nutrition Plan base on form data
    // Made by Nelson Perez
    @PostMapping("/add-nutrition-plan")
    public String addNutritionPlan(NutritionPlan nutritionPlan, Model model) {
        Trainee trainee = userService.findTraineeById(nutritionPlan.getTraineeId());
        nutritionPlan.setTrainee_nutri_plan(trainee);
        nutritionPlan.setCaloriesIntake(0);
        nutritionPlan.setLitersOfWaterTaken(0);
        userService.addNutritionPlan(nutritionPlan);
        model.addAttribute("currentuser", currentUser);
        return "nutritionistFunctions/nutritionistpage";
    }

    // createTrainingPlan
    // Shows user(Trainer) to create training plan form
    // Made by Nelson Perez
    @GetMapping("/createtrainingplan")
    public String createTrainingPlan(Model model) {
        Trainer trainer = currentUser.getTrainer();
        List<Trainee> traineeList = trainer.getTrainees();
        List<Trainee> traineesWithoutTrainingPLan = null;
        TrainingPlan trainingPlan = new TrainingPlan();
        if (traineeList.size() > 0) {
            traineesWithoutTrainingPLan = userService.getTraineesWithoutTrainingPLan(traineeList);
        }
        model.addAttribute("noPlanTrainees", traineesWithoutTrainingPLan);
        model.addAttribute("currentuser", currentUser);
        model.addAttribute("newtrainingplan", trainingPlan);
        return "trainerFunctions/createtrainingplan";
    }

    // addTrainingPlan
    // Create Training Plan base on form data
    // Made by Nelson Perez
    @PostMapping("/add-training-plan")
    public String addTrainingPlan(TrainingPlan trainingPlan, Model model) {
        Trainee trainee = userService.findTraineeById(trainingPlan.getTraineeId());
        trainingPlan.setTrainee_train_plan(trainee);
        trainingPlan.setTrainingHours(0);
        trainingPlan.setWeight(0);
        userService.addTrainingPlan(trainingPlan);
        model.addAttribute("currentuser", currentUser);
        return "trainerFunctions/trainerpage";
    }

    // checktraineestats
    // Show Mentors the stats of their asigned trainees
    // or show Trainee their own personal stats
    // Made by Nelson Perez
    @GetMapping("/checkstatsoftrainee")
    public String checktraineestats(Model model) {
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
                model.addAttribute("traineeList", traineeList);
                returnPage = "showtraineesstats";
                pageTitle = "Trainer";
                break;
            case 2:
                nutritionist = currentUser.getNutritionist();
                traineeList = nutritionist.getTrainees();
                model.addAttribute("traineeList", traineeList);
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
                model.addAttribute("traineeList", traineeList);
                pageTitle = "Trainer";
                break;
            case 2:
                nutritionist = currentUser.getNutritionist();
                traineeList = nutritionist.getTrainees();
                model.addAttribute("traineeList", traineeList);
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
    @GetMapping({"/usecase7"})
    public String assignNewMentor(Model model) {
        //TODO
          Trainee trainee = currentUser.getTrainee();
        List<Trainer> trainers = userService.findAllTrainers();
        List<Nutritionist> nutritionists = userService.findAllNutritionist();



        //TODO

        model.addAttribute("trainee", trainee);

        model.addAttribute("nutritionists", nutritionists);

        model.addAttribute("trainers", trainers);


        return "assignNewMentor";
    }

    @PostMapping("/assignNewMentor")
    public String assignNewMentors(Integer selectedNutritionist , Integer selectedTrainer) {
        Trainee trainee = userService.findTraineeById(7);
        Nutritionist newNutritionist ;
        Trainer newTrainer ;
        System.out.println("================Selected Nutritionist:    "+selectedNutritionist);
        System.out.println("================Selected Trainer:    "+selectedTrainer);
        // Check if selectedNutritionist is not null and find the corresponding Nutritionist

        if (selectedNutritionist != null) {
            newNutritionist = userService.findNutritionistById(selectedNutritionist);
            trainee.setNutritionist(newNutritionist);
        }

        // Check if selectedTrainer is not null and find the corresponding Trainer
        if (selectedTrainer != null) {
            newTrainer = userService.findTrainerById(selectedTrainer);
            // System.out.println("================new treinaer id    "+newTrainer.getId());
            trainee.setTrainer(newTrainer);
        }

        // Set the Nutritionist and Trainer for the Trainee
       /* if (trainee.getNutritionist() != null) {
            System.out.println("=================================="+trainee.getNutritionist());
        }*/
        //TODO
/*
        traineeRepo.save(trainee);
*/

        return "redirect:/assignNewMentor";
    }
    @GetMapping("/assignNewMentor")
    public String assignedNewMentor(Model model)
    {
        Trainee trainee = currentUser.getTrainee();
        model.addAttribute("trainee", trainee);
        model.addAttribute("nutritionists", userService.findAllNutritionist());
        model.addAttribute("trainers", userService.findAllTrainers());

        /*
        System.out.println("=================================="+trainee.getTrainer());
*/
        return "assignNewMentor";
    }


    // Made by Abdellah
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("userEntity", new UserEntity());
        return "register";
    }

    // Made by Abdellah
    @PostMapping("/register")
    public String registerUser(UserEntity userEntity) {
        userService.saveUser(userEntity);
        List<UserEntity> allUsers = userService.findAllUsers();
        UserEntity savedUser = allUsers.get(allUsers.size() - 1);
        switch (userEntity.getUserType()) {
            case 0:
                Trainee newtrainee = new Trainee(null, null, savedUser);
                //Made by nick
                // Assign Mentors to new Trainees
                List<Trainer> trainers = userService.findAllTrainers();
                List<Nutritionist> nutritionist = userService.findAllNutritionist();
                Nutritionist nutritionistWLeastClients = userService.findNutritionistWithLeastClients(nutritionist);
                Trainer trainerWLeastClients = userService.findTrainerWithLeastTrainees(trainers);
                newtrainee.setNutritionist(nutritionistWLeastClients);
                newtrainee.setTrainer(trainerWLeastClients);
                userService.saveTrainee(newtrainee);
                break;
            case 1:
                Trainer newtrainer = new Trainer(savedUser, true);
                userService.saveTrainer(newtrainer);
                break;
            default:
                Nutritionist newnutritionist = new Nutritionist(savedUser);
                userService.saveNutritionist(newnutritionist);
                break;
        }
        return "redirect:/showUsers";
    }

    // Made by Abdellah
    @GetMapping("/user-details")
    public String showUserDetails(Model model) {
        List<UserEntity> users = userService.findUsersByFirstName("Paul");
        model.addAttribute("users", users);
        return "findUsersByFirstName";
    }
    // Made by Abdellah
    @GetMapping({ "/showallUsers", "/showUsers" })
    public String showAllUsers(Model model) {
        List<UserEntity> users = userService.findAllUsers();
        model.addAttribute("users", users);
        return "showAllUsers";
    }
    // Made by Joel
    @GetMapping("/startNewTrainingSession")
    public String startNewTrainingSession(Model model) {
        // Fetch the UserEntity object for the current user
        // Create a new NewTrainingSession object and link it to the user
        NewTrainingSession newTrainingSession = new NewTrainingSession();
        List<UserEntity> something = userService.findAllUsers();
        currentUser = something.get(12);
        model.addAttribute("newTrainingSession", newTrainingSession);
        return "startNewTrainingSession";
    }
    // Made by Joel
    @PostMapping("/startNewTrainingSession")
    public String handleNewTrainingSession(NewTrainingSession newTrainingSession) {
        Trainer trainer = userService.findAvailableTrainers().get(0); // This is just an example. You should add error // handling here.
        newTrainingSession.setTrainer_session(trainer);
        newTrainingSession.setTrainee_session(currentUser.getTrainee());
        userService.saveNewTrainingSession(newTrainingSession);
        return "redirect:/showSession";
    }

    // Made by Joel
    @GetMapping("/showSession")
    public String showNewTrainingSession(Model model) {
        // Fetch the UserEntity object for the current user
        // Create a new NewTrainingSession object and link it to the user
        model.addAttribute("user", currentUser);
        return "showTrainingSession";
    }









}

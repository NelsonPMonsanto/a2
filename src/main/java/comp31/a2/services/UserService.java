package comp31.a2.services;

import java.util.List;

import org.springframework.stereotype.Service;

import comp31.a2.model.repositories.DepartmentRepo;
import comp31.a2.model.repositories.UserRepo;
import comp31.a2.model.entities.User;
import comp31.a2.model.entities.Department;



@Service
public class UserService {
    
    UserRepo userRepo;
    DepartmentRepo departmentRepo;

    public UserService(UserRepo userRepo, DepartmentRepo departmentRepo) {
        this.userRepo = userRepo;
        this.departmentRepo = departmentRepo;
    }

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    public List<Department> getAllDepartments() {
        return departmentRepo.findAll();
    }
}

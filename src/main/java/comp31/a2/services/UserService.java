package comp31.a2.services;

import org.springframework.stereotype.Service;

import comp31.a2.model.repositories.DepartmentRepo;
import comp31.a2.model.repositories.UserRepo;

@Service
public class UserService {
    
    UserRepo userRepo;
    DepartmentRepo departmentRepo;

    public UserService(UserRepo userRepo, DepartmentRepo departmentRepo) {
        this.userRepo = userRepo;
        this.departmentRepo = departmentRepo;
    }
}

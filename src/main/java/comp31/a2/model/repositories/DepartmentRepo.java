package comp31.a2.model.repositories;
import org.springframework.data.repository.CrudRepository;

import comp31.a2.model.entities.Department;

import java.util.List;


public interface DepartmentRepo extends CrudRepository<Department, Long> {
    List<Department> findAll();
    
}

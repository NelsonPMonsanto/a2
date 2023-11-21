package comp31.a2.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    String firstName;
    String lastName;



    @ManyToOne
    @JoinColumn(name="fkey_dept")
    Department department;

    

    public User( String firstName, String lastName, Department department) {
        System.out.println("Creating " + firstName + " " + lastName + " " + department);
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = department;
    }

}
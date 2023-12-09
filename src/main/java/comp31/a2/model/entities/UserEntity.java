package comp31.a2.model.entities;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class UserEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;
    String username;
    String firstName;
    String lastName;
    String password;
    Integer userType; //0-Trainee 1-Trainer 2-Nutrtitionist
    @OneToOne(mappedBy = "user_trainer")
    Trainer trainer;

    @OneToOne(mappedBy = "user_trainee")
    Trainee trainee;

    @OneToOne(mappedBy = "user_nutritionist")
    Nutritionist nutritionist;

    public UserEntity(String username, String firstName, String lastName, String password, Integer userType) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.userType = userType;
    }
}


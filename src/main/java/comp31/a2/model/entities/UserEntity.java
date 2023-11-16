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
    Boolean ifTrainer;
    @OneToOne(mappedBy = "user_trainer")
    Trainer trainer;

    Boolean ifTrainee;
    @OneToOne(mappedBy = "user_trainee")
    Trainee trainee;

    Boolean ifNutritionist;
    @OneToOne(mappedBy = "user_nutritionist")
    Nutritionist nutritionist;
    public UserEntity(String username, String firstName, String lastName, String password, Boolean ifTrainer,
            Boolean ifTrainee, Boolean ifNutritionist) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.ifTrainer = ifTrainer;
        this.ifTrainee = ifTrainee;
        this.ifNutritionist = ifNutritionist;
    }





    
}


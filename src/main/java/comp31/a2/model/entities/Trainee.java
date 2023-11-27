package comp31.a2.model.entities;

// import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
public class Trainee {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;
    // String username;
    // String firstName;
    // String lastName;
    // String password;
    @ManyToOne
    @JoinColumn(name="fkey_nutritionist")
    Nutritionist nutritionist;
    @ManyToOne
    @JoinColumn(name="fkey_trainer")
    Trainer trainer;
    @OneToOne()
    @JoinColumn(name = "user_id")
    UserEntity user_trainee;
    @OneToOne(mappedBy = "trainee_session")
    NewTrainingSession newTrainingSession;

    public Trainee(Nutritionist nutritionist, Trainer trainer, UserEntity user_trainee) {
        this.user_trainee = user_trainee;
        this.nutritionist = nutritionist;
        this.trainer = trainer;
    }

    

    // public Trainee(String username, String firstName, String lastName, String password, Nutritionist nutritionist,
    //         Trainer trainer) {
    //     this.username = username;
    //     this.firstName = firstName;
    //     this.lastName = lastName;
    //     this.password = password;
    //     this.nutritionist = nutritionist;
    //     this.trainer = trainer;
    // }



    
}

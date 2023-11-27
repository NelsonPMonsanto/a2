package comp31.a2.model.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Trainer {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;

    // String username;
    // String firstName;
    // String lastName;
    // String password; 
    @OneToMany(mappedBy = "trainer")
    List<Trainee> trainees;
    @OneToOne()
    @JoinColumn(name = "user_id")
    UserEntity user_trainer;
    @Column(name = "is_available") //NOV26
    Boolean isAvailable; //NOV26


    public Trainer(UserEntity user_trainer, Boolean isAvailable) {
        this.user_trainer = user_trainer;
        this.isAvailable = isAvailable;
    }

    // public Trainer(String username, String firstName, String lastName, String password) {
    //     this.username = username;
    //     this.firstName = firstName;
    //     this.lastName = lastName;
    //     this.password = password;
    // }


}

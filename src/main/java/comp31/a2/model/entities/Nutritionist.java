package comp31.a2.model.entities;

import java.util.List;

// import jakarta.persistence.CascadeType;
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
public class Nutritionist {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;
    // String username;
    // String firstName;
    // String lastName;
    // String password;
    @OneToMany(mappedBy = "nutritionist")
    List<Trainee> trainees;
    @OneToOne()
    @JoinColumn(name = "ntr_user_id")
    UserEntity user_nutritionist;

    

    public Nutritionist(UserEntity user_nutritionist) {
        this.user_nutritionist = user_nutritionist;
    }

    // public Nutritionist(String username, String firstName, String lastName, String password) {
    //     this.username = username;
    //     this.firstName = firstName;
    //     this.lastName = lastName;
    //     this.password = password;
    // }


}

package comp31.a2.model.entities;

import java.util.List;

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
    @OneToMany(mappedBy = "trainer")
    List<Trainee> trainees;
    @OneToOne()
    @JoinColumn(name = "trr_user_id")
    UserEntity user_trainer;
    @Column(name = "is_available") // NOV26
    Boolean isAvailable; // NOV26
    @OneToOne(mappedBy = "trainer_session")
    NewTrainingSession newTrainingSession;

    public Trainer(UserEntity user_trainer, Boolean isAvailable) {
        this.user_trainer = user_trainer;
        this.isAvailable = isAvailable;
    }

    public List<Trainee> getTrainees() {
        return this.trainees;
    }

}

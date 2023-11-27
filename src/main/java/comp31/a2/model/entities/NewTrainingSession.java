package comp31.a2.model.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.JoinColumn;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class NewTrainingSession {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;

    @OneToOne
    @JoinColumn(name="trainee_id")
    Trainee trainee_session;

    @OneToOne
    @JoinColumn(name="trainer_id")
    Trainer trainer_session;

    LocalDateTime startTime;
    LocalDateTime endTime;
    String feedback;
    String evaluation;

    public NewTrainingSession(Trainee trainee, Trainer trainer, LocalDateTime startTime, LocalDateTime endTime) {
        this.trainee_session = trainee;
        this.trainer_session = trainer;
        this.startTime = startTime;
        this.endTime = endTime;
    }  
}

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
    private Integer id;

    @ManyToOne
    @JoinColumn(name="trainee_id")
    private Trainee trainee;

    @ManyToOne
    @JoinColumn(name="trainer_id")
    private Trainer trainer;

    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String feedback;
    private String evaluation;

    public NewTrainingSession(Trainee trainee, Trainer trainer, LocalDateTime startTime, LocalDateTime endTime) {
        this.trainee = trainee;
        this.trainer = trainer;
        this.startTime = startTime;
        this.endTime = endTime;
    }  
}

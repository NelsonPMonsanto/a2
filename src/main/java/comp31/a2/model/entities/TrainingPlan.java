package comp31.a2.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class TrainingPlan {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;

    String goal;
    String musclePriority;
    String excercisetoavoid;

    Integer weight;
    Integer trainingHours;

    @OneToOne()
    @JoinColumn(name = "fk_trainee")
    Trainee trainee_train_plan;

    Integer traineeId;

    public TrainingPlan(String goal, String musclePriority, String excercisetoavoid,Integer weight,Integer trainingHours, Trainee trainee_train_plan) {
        this.goal = goal;
        this.musclePriority = musclePriority;
        this.excercisetoavoid = excercisetoavoid;
        this.trainee_train_plan = trainee_train_plan;
        this.weight = weight;
        this.trainingHours = trainingHours;
    }

}

package comp31.a2.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
@Data
@NoArgsConstructor
public class NutritionPlan {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;

    String goal;
    String foodToAvoid;
    String foodToPriotize;

    Integer caloriesIntake;
    Integer litersOfWaterTaken;

    @OneToOne()
    @JoinColumn(name = "fk_trainee")
    Trainee trainee_nutri_plan;

    Integer traineeId;

    public NutritionPlan(String goal, String foodToAvoid, String foodToPriotize,Integer caloriesIntake,Integer litersOfWaterTaken, Trainee trainee_nutri_plan) {
        this.goal = goal;
        this.foodToAvoid = foodToAvoid;
        this.foodToPriotize = foodToPriotize;
        this.caloriesIntake = caloriesIntake;
        this.litersOfWaterTaken = litersOfWaterTaken;
        this.trainee_nutri_plan = trainee_nutri_plan;
    }
}

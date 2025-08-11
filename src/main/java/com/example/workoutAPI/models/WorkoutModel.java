package com.example.workoutAPI.models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.util.List;
import java.util.UUID;

public class WorkoutModel extends RepresentationModel<WorkoutModel> implements Serializable{
    public static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idWorkout;
    private String name;
    private String objective;
    private List<ExerciseModel> exercises;
    private DayOfWeek trainingDay;

    public UUID getIdWorkout() {
        return idWorkout;
    }

    public void setIdWorkout(UUID idWorkout) {
        this.idWorkout = idWorkout;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getObjective() {
        return objective;
    }

    public void setObjective(String objective) {
        this.objective = objective;
    }

    public List<ExerciseModel> getExercises() {
        return exercises;
    }

    public void setExercises(List<ExerciseModel> exercises) {
        this.exercises = exercises;
    }

    public DayOfWeek getTrainingDay() {
        return trainingDay;
    }

    public void setTrainingDay(DayOfWeek trainingDay) {
        this.trainingDay = trainingDay;
    }
}

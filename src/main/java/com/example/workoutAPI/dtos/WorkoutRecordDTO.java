package com.example.workoutAPI.dtos;

import com.example.workoutAPI.models.ExerciseModel;
import jakarta.validation.constraints.NotBlank;

import java.time.DayOfWeek;
import java.util.List;

public record WorkoutRecordDTO(@NotBlank String name, @NotBlank String objective, @NotBlank List<ExerciseModel> exercises, @NotBlank DayOfWeek trainingDay) {
}

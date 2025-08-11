package com.example.workoutAPI.repositories;

import com.example.workoutAPI.models.WorkoutModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface WorkoutRepository extends JpaRepository<WorkoutModel, UUID> {
}

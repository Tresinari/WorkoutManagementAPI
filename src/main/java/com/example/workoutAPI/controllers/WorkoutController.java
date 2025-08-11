package com.example.workoutAPI.controllers;

import com.example.workoutAPI.dtos.WorkoutRecordDTO;
import com.example.workoutAPI.models.WorkoutModel;
import com.example.workoutAPI.repositories.WorkoutRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class WorkoutController {

    @Autowired
    WorkoutRepository workoutRepository;

    @PostMapping("/workouts")
    public ResponseEntity<WorkoutModel> saveWorkout(@RequestBody @Valid WorkoutRecordDTO workoutRecordDTO){
        var workoutModel = new WorkoutModel();
        BeanUtils.copyProperties(workoutRecordDTO, workoutModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(workoutRepository.save(workoutModel));
    }

    @GetMapping("/workouts")
    public ResponseEntity<List<WorkoutModel>> getAllWorkouts(){
        List<WorkoutModel> workoutsList = workoutRepository.findAll();

        if (!workoutsList.isEmpty()){
            for (WorkoutModel workout : workoutsList){
                UUID id = workout.getIdWorkout();
                workout.add(linkTo(methodOn(WorkoutController.class).getOneWorkout(id)).withSelfRel());
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(workoutsList);
    }

    @GetMapping("/workouts/{id}")
    public ResponseEntity<Object> getOneWorkout(@PathVariable(value = "id") UUID id){
        Optional<WorkoutModel> workoutO = workoutRepository.findById(id);

        if (workoutO.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Workout not found!");
        }
        workoutO.get().add(linkTo(methodOn(WorkoutController.class).getAllWorkouts()).withRel("Workouts list"));
        return ResponseEntity.status(HttpStatus.OK).body(workoutO);
    }

    @PutMapping("/workouts/{id}")
    public ResponseEntity<Object> updateWorkout(@PathVariable(value = "id") UUID id, @RequestBody @Valid WorkoutRecordDTO workoutRecordDTO){
        Optional<WorkoutModel> workoutO = workoutRepository.findById(id);
        if (workoutO.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Workout not found");
        }
        var workoutModel = workoutO.get();
        BeanUtils.copyProperties(workoutRecordDTO, workoutModel);
        return ResponseEntity.status(HttpStatus.OK).body(workoutRepository.save(workoutModel));
    }

    @DeleteMapping("/workouts/{id}")
    public ResponseEntity<Object> deleteWorkout(@PathVariable(value = "id") UUID id){
        Optional<WorkoutModel> workoutO = workoutRepository.findById(id);
        if (workoutO.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Workout not found.");
        }
        workoutRepository.delete(workoutO.get());
        return ResponseEntity.status(HttpStatus.OK).body("Workout deleted");
    }

}

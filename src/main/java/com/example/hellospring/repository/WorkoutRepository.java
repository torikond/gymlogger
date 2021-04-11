package com.example.hellospring.repository;

import com.example.hellospring.domain.Workout;

import java.util.List;
import java.util.Optional;

public interface WorkoutRepository {

    Workout save(Workout workout);
    List<Workout> findByEmail(String email);
}
package com.example.hellospring.service;

import com.example.hellospring.domain.Member;
import com.example.hellospring.domain.Workout;
import com.example.hellospring.repository.MemberRepository;
import com.example.hellospring.repository.WorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

// Service will be registered in springboot container, use autowired, so registered object can be resed
// @Service way 1 to register -> component scanner
@Transactional
public class WorkoutService {

    //private final TMemberRepository memberRepository = new TMemberRepository();
    private final WorkoutRepository workoutRepository;

    // inject
    @Autowired // way 1 to register
    public WorkoutService(WorkoutRepository workoutRepository){
        this.workoutRepository = workoutRepository;
    }

    public Long join(Workout workout){
        // if returns, name is already used, can't use name
        workoutRepository.save(workout);
        return workout.getId();
    }

    public List<Workout> getWorkouts(String email){
        List<Workout> result = workoutRepository.findByEmail(email);
        return result;
    }

}

package com.example.hellospring.repository;

import com.example.hellospring.domain.Member;
import com.example.hellospring.domain.Workout;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaWorkoutRepository implements WorkoutRepository{

    private final EntityManager em;

    // constructor
    public JpaWorkoutRepository(EntityManager em){
        this.em = em;
    }

    @Override
    public Workout save(Workout workout) {
        em.persist(workout);
        return workout;
    }

    @Override
    public List<Workout> findByEmail(String email) {
        // not primary key
        List<Workout> result = em.createQuery("select w from Workout w where w.email = :email", Workout.class).setParameter("email", email).getResultList();
        return result;
    }
}

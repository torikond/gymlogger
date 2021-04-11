package com.example.hellospring.controller;

import com.example.hellospring.domain.Member;

public class WorkoutForm {

    private Member member;
    private String category;
    private String exercise;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getExercise() {
        return exercise;
    }

    public void setExercise(String exercise) {
        this.exercise = exercise;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }
}

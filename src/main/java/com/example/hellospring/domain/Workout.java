package com.example.hellospring.domain;

import javax.persistence.*;

@Entity
public class Workout {

    // attribute mapped to table in database
    // id is primary key
    // automatically increased
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // if column dif name

    @ManyToOne
    @JoinColumn(name = "email")
    private Member member;

    @Column(name = "category")
    private String category;

    @Column(name = "exercise")
    private String exercise;

    public String getExercise() {
        return exercise;
    }
    public void setExercise(String exercise) {
        this.exercise = exercise;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }
}

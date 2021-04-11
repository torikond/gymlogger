package com.example.hellospring.controller;

import com.example.hellospring.domain.Member;
import com.example.hellospring.domain.Workout;
import com.example.hellospring.service.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class WorkoutController {

    // @Autowired (field injection)
    private final WorkoutService workoutService;

    // constructor injection (other kinds: field injection, setter injection)
    // constructor recommended
    @Autowired //way 1 to register
    public WorkoutController(WorkoutService workoutService) {
        this.workoutService = workoutService;
    }

    @GetMapping("workouts/create")
    public String createAccount() {
        return "workouts/create"; //direction / html page
        //name passed to /members/new -> use post method (from html)
    }

    @PostMapping("workouts/create")
    public String create(WorkoutForm form) {
        //to send data to server, need MemberForm
        //called at submit button from createMember
        Workout workout = new Workout();
        workout.setCategory(form.getCategory());
        workout.setExercise(form.getExercise());
        workout.setMember(form.getMember());

        workoutService.join(workout);
        //when click submit want it to go to previous page
        return "redirect:/";


    }

    @GetMapping("/workouts/new")
    public String list(Model model) {
        Member member = new Member();
        member.setEmail("torikondratenko@gmail.com");
        List<Workout> workouts = workoutService.getWorkouts(member.getEmail());
        model.addAttribute("workouts", workouts);
        // need key to access data which is ""
        return "workouts/new";
    }
}

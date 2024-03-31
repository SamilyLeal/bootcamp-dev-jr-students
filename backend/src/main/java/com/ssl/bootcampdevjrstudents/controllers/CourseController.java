package com.ssl.bootcampdevjrstudents.controllers;

import com.ssl.bootcampdevjrstudents.models.Course;
import jakarta.annotation.PostConstruct;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {
    List<Course> courses = new ArrayList<>();

    @PostConstruct
    public void init(){
        courses.add(new Course(1, "ADS"));
        courses.add(new Course(2, "GTI"));
        courses.add(new Course(3, "CS"));
        courses.add(new Course(4, "MED"));
    }

    @GetMapping
    public ResponseEntity<List<Course>> getCourses(){
        return ResponseEntity.ok(courses);
    }
}

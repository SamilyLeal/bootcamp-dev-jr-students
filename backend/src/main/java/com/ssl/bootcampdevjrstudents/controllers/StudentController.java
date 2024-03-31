package com.ssl.bootcampdevjrstudents.controllers;

import com.ssl.bootcampdevjrstudents.models.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("students")
@CrossOrigin
public class StudentController {

    List<Student> students = new ArrayList<>();

    @PostConstruct
    public void init(){
        students.add(new Student(1, "octopus", "oct@gmail.com",
                            "11944556677",1,1));

        students.add(new Student(2, "shrimp", "shr@gmail.com",
                "11944556699",2,2));

        students.add(new Student(3, "whale", "wha@gmail.com",
                "11944556688",3,10));
    }

    @GetMapping
    public ResponseEntity<List<Student>> getStudents(){
        return ResponseEntity.ok(students);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable int id){
        Student student = students.stream()
                .filter(s -> s.getId() == id)
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found with the given id: "+ id));

        return ResponseEntity.ok(student);
    }

    @PostMapping
    public ResponseEntity<Student> save(@RequestBody Student student){
        student.setId(students.size() + 1);
        students.add(student);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(student.getId())
                .toUri();

        return ResponseEntity.created(location).body(student);
    }
}

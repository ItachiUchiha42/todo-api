package com.example.todoapi.controllers;

import com.example.todoapi.models.Student;
import com.example.todoapi.services.StudentService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {
    @Autowired
    private StudentService service;

    @GetMapping(value = "/all")
    public List<Student> getAll() {
        return service.getAllStudents();
    }

    @PostMapping(value = "/create")
    public String create(@RequestBody Student student) {
        return service.createSingleStudent(student);
    }

    @PutMapping(value = "/update/{id}")
    public Student update(@PathVariable String id, @RequestBody Student student) {
        return service.updateSingleStudent(id, student);
    }

    @DeleteMapping(value = "/delete/{id}")
    public String delete(@PathVariable String id) {
        return service.deleteSingleStudent(id);
    }
}

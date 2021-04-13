package com.example.todoapi.services;

import com.example.todoapi.models.Student;
import com.example.todoapi.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public String createSingleStudent(Student student) {
        Student student1 = studentRepository.insert(student);
        return student1.getId();
    }

    public Student updateSingleStudent(String id, Student student) {
        Optional<Student> student1 = studentRepository.findById(id);
        if (student1.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, id + " not found");
        }
        if (student.getName() != null) {
            student1.get().setName(student.getName());
        }
        if (student.getAge() != null && student.getAge().intValue() != 0) {
            student1.get().setAge(student.getAge());
        }
        if (student.getGrade() != null && student.getGrade() != 0) {
            student1.get().setGrade(student.getGrade());
        }
        return studentRepository.save(student1.get());
    }

    public String deleteSingleStudent(String id) {
        if (!studentRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, id + " not found");
        }
        studentRepository.deleteById(id);
        return id;
    }
}

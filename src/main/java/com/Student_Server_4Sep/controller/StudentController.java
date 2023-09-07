package com.Student_Server_4Sep.controller;

import com.Student_Server_4Sep.model.Student;
import com.Student_Server_4Sep.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @PostMapping
    public void createNewStudent(@RequestBody Student student){
       studentRepository.createNewStudent(student);
    }

    @PutMapping
    public void updateExistingStudent(@RequestBody Student student){
        studentRepository.updateExistingStudent(student);
    }

    @DeleteMapping
    public void deleteExistingStudentById(@RequestParam int id){
        studentRepository.deleteExistingStudentById(id);
    }

    @GetMapping
    public List<Student> getStudentsByListOfIds(@RequestBody List<Integer> ids){
        return studentRepository.getStudentsByListOfIds(ids);
    }

}

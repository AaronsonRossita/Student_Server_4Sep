package com.Student_Server_4Sep.repository;

import com.Student_Server_4Sep.model.Student;

import java.util.List;

public interface StudentRepository {

    void createNewStudent(Student student);
    void updateExistingStudent(Student student);
    void deleteExistingStudentById(int id);

    Student getStudentById(int id);
    List<Student> getAllStudents();
    List<Student> getStudentsByFirstName(String firstName);
    List<String> getStudentEmailsByFirstName(String firstName);
    List<Student> getStudentsByListOfIds(List<Integer> ids);


}

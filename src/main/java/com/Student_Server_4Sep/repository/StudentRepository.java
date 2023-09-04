package com.Student_Server_4Sep.repository;

import com.Student_Server_4Sep.model.Student;

import java.util.List;

public interface StudentRepository {

    void createNewStudent(Student student);
    void updateExistingStudent(Student student);
    void deleteExistingStudentById(int id);

    List<Student> getAllStudents();
    List<Student> getAllStudentsByFirstName(String firstName);
    List<String> getAllEmailsByFirstName(String firstName);
    List<Student> getAllStudentsByIdList(List<Integer> idList);




}

package com.Student_Server_4Sep.repository;

import com.Student_Server_4Sep.model.Student;
import com.Student_Server_4Sep.repository.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentRepositoryImpl implements StudentRepository {

    private static final String STUDENT_TABLE_NAME = "student";
    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public void createNewStudent(Student student) {
        String sql = "INSERT INTO " + STUDENT_TABLE_NAME + " (first_name, last_name, email) VALUES (?,?,?)";
        jdbcTemplate.update(sql,student.getFirstName(),student.getLastName(),student.getEmail());
    }

    @Override
    public void updateExistingStudent(Student student) {
        String sql = "UPDATE " + STUDENT_TABLE_NAME + " SET first_name = ?, last_name = ?, email = ? WHERE id = ?";
        jdbcTemplate.update(sql,student.getFirstName(),student.getLastName(),student.getEmail(),student.getId());
    }

    @Override
    public void deleteExistingStudentById(int id) {
        String sql = "DELETE FROM " + STUDENT_TABLE_NAME + " WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    // query = List<Object>,
    // queryForObject = Object,
    // queryForList = List<Primitive>

    @Override
    public Student getStudentById(int id) {
        String sql = "SELECT * FROM " + STUDENT_TABLE_NAME + " WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new StudentMapper(), id);
    }

    @Override
    public List<Student> getAllStudents() {
        String sql = "SELECT * FROM " + STUDENT_TABLE_NAME;
        return jdbcTemplate.query(sql, new StudentMapper());
    }

    @Override
    public List<Student> getStudentsByFirstName(String firstName) {
        String sql = "SELECT * FROM " + STUDENT_TABLE_NAME + " WHERE first_name = ?";
        return jdbcTemplate.query(sql,new StudentMapper(),firstName);
    }

    @Override
    public List<String> getStudentEmailsByFirstName(String firstName) {
        String sql = "SELECT email FROM " + STUDENT_TABLE_NAME + " WHERE first_name = ?";
        return jdbcTemplate.queryForList(sql,String.class,firstName);
    }

//    @Override
//    public ArrayList<Student> getStudentsByListOfIds(int[] ids) {
//        ArrayList<Student> students = new ArrayList<>();
//        for (int i = 0; i < ids.length; i++) {
//            students.add(getStudentById(ids[i]));
//        }
//        return students;
//    }


    @Override
    public List<Student> getStudentsByListOfIds(List<Integer> ids) {
        String sql = "SELECT * FROM " + STUDENT_TABLE_NAME + " WHERE id IN ?";
        return jdbcTemplate.query(sql,new StudentMapper(),arrayToRange(ids));
    }

    private static String arrayToRange(List<Integer> arr){
        String range = "(";
        for (int i = 0; i < arr.size(); i++) {
            if(i == arr.size() -1){
                range += arr.get(i);
            }else{
                range += arr.get(i) + ",";
            }
        }
        range += ")";
        return range;
    }
}

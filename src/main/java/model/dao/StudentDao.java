package model.dao;

import model.Student;
import java.util.List;

public interface StudentDao {

    Student findById(Integer id);
    List<Student> findAll();
    List<Student> findByCourse(Integer id);
    void deleteById(Integer id);
    void update(Student student);
    void insert(Student student);
}

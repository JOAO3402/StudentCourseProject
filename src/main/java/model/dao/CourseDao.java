package model.dao;

import model.Course;
import java.util.List;

public interface CourseDao {

    Course findById(Integer id);
    List<Course> findAll();
    void deleteById(Integer id);
    void update(Course course);
    void insert(Course course);
}

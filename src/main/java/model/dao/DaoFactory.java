package model.dao;

import model.connection.BD;
import model.dao.imp.CourseDaoJDBC;
import model.dao.imp.StudentDaoJDBC;

public class DaoFactory {

    public CourseDao createCourseDao(){
        return new CourseDaoJDBC(BD.getConnection());
    }

    public StudentDao createStudentDao(){
        return new StudentDaoJDBC(BD.getConnection());
    }
}

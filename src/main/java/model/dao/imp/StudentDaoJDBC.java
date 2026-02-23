package model.dao.imp;

import model.Course;
import model.connection.BD;
import model.connection.exception.BdException;
import model.dao.StudentDao;
import model.Student;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentDaoJDBC implements StudentDao {

    private Connection conn;

    public StudentDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void deleteById(Integer id) {
        PreparedStatement st = null;
        try{
            st = conn.prepareStatement(
                    "DELETE FROM student WHERE Id = ?");
            st.setInt(1, id);

            st.executeUpdate();
        }
        catch(SQLException e){
            throw new BdException(e.getMessage());
        }
        finally{
            BD.closeStatement(st);
        }
    }

    public static void deleteByCourse(Connection conn, Integer id){
        PreparedStatement st = null;
        try{
            st = conn.prepareStatement(
                    "DELETE FROM student WHERE CourseId = ?");
            st.setInt(1, id);

            st.executeUpdate();
        }
        catch(SQLException e){
            throw new BdException(e.getMessage());
        }
        finally{
            BD.closeStatement(st);
        }
    }

    @Override
    public Student findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try{
            st =conn.prepareStatement(
                    "SELECT student.*,course.Name as CouName "
                    + "FROM student INNER JOIN course "
                    + "ON student.CourseId = course.Id "
                    + "WHERE student.Id = ?");

            st.setInt(1, id);
            rs = st.executeQuery();

            if(rs.next()){
                Course course = instantiateCourse(rs);
                Student student = instantiateStudent(rs, course);
                return student;
            }

            return null;
        }
        catch(SQLException e){
            throw new BdException(e.getMessage());
        }
        finally{
            BD.closeStatement(st);
            BD.closeResultSet(rs);
        }
    }

    @Override
    public List<Student> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;
        try{
            st = conn.prepareStatement(
                    "SELECT student.*,course.Name as CouName "
                            + "FROM student INNER JOIN course "
                            + "ON student.CourseId = course.Id "
                            + "ORDER BY Id");

            rs = st.executeQuery();

            List<Student> list = new ArrayList<>();
            Map<Integer, Course> map = new HashMap<>();

            while(rs.next()){
                Course course = map.get(rs.getInt("CourseId"));
                if(course == null){
                    course = instantiateCourse(rs);
                    map.put(rs.getInt("CourseId"), course);
                }

                Student student = instantiateStudent(rs, course);
                list.add(student);
            }

            return list;
        }
        catch(SQLException e){
            throw new BdException(e.getMessage());
        }
        finally{
            BD.closeStatement(st);
            BD.closeResultSet(rs);
        }
    }

    @Override
    public List<Student> findByCourse(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try{
            st = conn.prepareStatement(
                    "SELECT student.*,course.Name as CouName "
                    + "FROM student INNER JOIN course "
                    + "ON student.CourseId = course.Id "
                    + "WHERE CourseId = ? "
                    + "ORDER BY Id");
            st.setInt(1, id);

            rs = st.executeQuery();

            List<Student> list = new ArrayList<>();
            Map<Integer, Course> map = new HashMap<>();

            while(rs.next()){
                Course course = map.get(rs.getInt("CourseId"));
                if(course == null){
                    course = instantiateCourse(rs);
                    map.put(rs.getInt("CourseId"), course);
                }

                Student student = instantiateStudent(rs, course);
                list.add(student);
            }

            return list;
        }
        catch(SQLException e){
            throw new BdException(e.getMessage());
        }
        finally{
            BD.closeStatement(st);
            BD.closeResultSet(rs);
        }
    }

    private Student instantiateStudent(ResultSet rs, Course course) throws SQLException{
        Student student = new Student();
        student.setId(rs.getInt("Id"));
        student.setName(rs.getString("Name"));
        student.setBirthDate(rs.getDate("Birthdate"));
        student.setGrade(rs.getDouble("Grade"));
        student.setCourse(course);
        return student;
    }

    private Course instantiateCourse(ResultSet rs) throws SQLException{
        Course course = new Course();
        course.setId(rs.getInt("CourseId"));
        course.setName(rs.getString("CouName"));
        return course;
    }

    @Override
    public void update(Student student) {
        PreparedStatement st = null;
        try{
            st = conn.prepareStatement(
                    "UPDATE student "
                    + "SET Name = ?, Birthdate = ?, Grade = ?, CourseId = ? "
                    + "WHERE Id = ?");

            st.setString(1, student.getName());
            st.setDate(2, new java.sql.Date(student.getBirthDate().getTime()));
            st.setDouble(3, student.getGrade());
            st.setInt(4, student.getCourse().getId());
            st.setInt(5, student.getId());

            st.executeUpdate();
        }
        catch(SQLException e){
            throw new BdException(e.getMessage());
        }
        finally{
            BD.closeStatement(st);
        }
    }

    @Override
    public void insert(Student student) {
        PreparedStatement st = null;
        try{
            st = conn.prepareStatement(
                    "INSERT INTO student "
                    + "(Name, Birthdate, Grade, CourseId) "
                    + "VALUES "
                    + "(?, ?, ?, ?)",
                    PreparedStatement.RETURN_GENERATED_KEYS);

            st.setString(1, student.getName());
            st.setDate(2, new java.sql.Date(student.getBirthDate().getTime()));
            st.setDouble(3, student.getGrade());
            st.setInt(4, student.getCourse().getId());

            int rowsAffected = st.executeUpdate();

            if(rowsAffected > 0){
                ResultSet rs = st.getGeneratedKeys();
                if(rs.next()){
                    int id = rs.getInt(1);
                    student.setId(id);
                    System.out.println("Done! Student ID is: " + student.getId());
                }
                BD.closeResultSet(rs);
            }
            else{
                throw new BdException("Unexpected error! No rows affected");
            }
        }
        catch(SQLException e){
            throw new BdException(e.getMessage());
        }
        finally{
            BD.closeStatement(st);
        }
    }
}

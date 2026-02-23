package model.dao.imp;

import model.connection.BD;
import model.connection.exception.BdException;
import model.dao.CourseDao;
import model.Course;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseDaoJDBC implements CourseDao {

    private Connection conn;

    public CourseDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public Course findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try{
            st = conn.prepareStatement(
                    "SELECT * FROM course WHERE course.Id = ?");

            st.setInt(1, id);
            rs = st.executeQuery();

            if(rs.next()){
                Course course = instantiateCourse(rs);
                return course;
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

    private Course instantiateCourse(ResultSet rs) throws SQLException{
        Course course = new Course();
        course.setId(rs.getInt("Id"));
        course.setName(rs.getString("Name"));
        return course;
    }

    @Override
    public List<Course> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;
        try{
            st = conn.prepareStatement(
                    "SELECT * FROM course ORDER BY Id;");

            rs = st.executeQuery();

            List<Course> list = new ArrayList<>();

            while(rs.next()){
                Course course = instantiateCourse(rs);
                list.add(course);
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
    public void update(Course course) {
        PreparedStatement st = null;
        try{
            st = conn.prepareStatement(
                    "UPDATE course "
                    + "SET Name = ? "
                    + "WHERE Id = ?");

            st.setString(1, course.getName());
            st.setInt(2, course.getId());

            st.executeUpdate();

        } catch(SQLException e) {
            throw new BdException(e.getMessage());
        }
        finally{
            BD.closeStatement(st);
        }
    }

    @Override
    public void insert(Course course) {
        PreparedStatement st = null;
        try{
            st = conn.prepareStatement(
                    "INSERT INTO course (Name) VALUES (?)", PreparedStatement.RETURN_GENERATED_KEYS);

            st.setString(1, course.getName());

            int rowsAffected = st.executeUpdate();

            if(rowsAffected > 0){
                ResultSet rs = st.getGeneratedKeys();
                if(rs.next()){
                    int id = rs.getInt(1);
                    course.setId(id);
                    System.out.println("Done! Course ID is: " + course.getId());
                }
                BD.closeResultSet(rs);
            } else{
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

    @Override
    public void deleteById(Integer id) {
        PreparedStatement st = null;
        try{
            conn.setAutoCommit(false);

            StudentDaoJDBC.deleteByCourse(conn, id);

            st = conn.prepareStatement(
                    "DELETE FROM course WHERE Id = ?");
            st.setInt(1, id);

            st.executeUpdate();
            conn.commit();
        }
        catch (SQLException e){
            try{
                conn.rollback();
                throw new BdException("Transaction rolled back! Caused by: " + e.getMessage());
            }
            catch (SQLException e1){
                throw new BdException("Error trying to rollback! Caused by: " + e1.getMessage());
            }
        }
        finally{
            BD.closeStatement(st);
        }
    }
}

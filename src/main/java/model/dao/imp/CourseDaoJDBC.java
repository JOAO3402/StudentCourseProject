package model.dao.imp;

import model.connection.BD;
import model.connection.exception.BdException;
import model.dao.CourseDao;
import model.Course;
import model.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class CourseDaoJDBC implements CourseDao {

    private Connection conn;

    public CourseDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public Course findById(Integer id) {
        return null;
    }

    @Override
    public List<Course> findAll() {
        return List.of();
    }

    @Override
    public void update(Student student) {

    }

    @Override
    public void insert(Student student) {

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

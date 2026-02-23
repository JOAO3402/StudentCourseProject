package model.connection;

import model.connection.exception.BdException;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class BD {

    public static Connection getConnection(){

        Connection conn = null;
        try{
            Properties props = loadProperties();
            String url = props.getProperty("dburl");
            String user = props.getProperty("user");
            String password = props.getProperty("password");
            conn = DriverManager.getConnection(url, user, password);
        }
        catch(SQLException e){
            throw new BdException(e.getMessage());
        }

        return conn;
    }

    private static Properties loadProperties() {
        try (FileInputStream fs = new FileInputStream("bd.properties")) {
            Properties props = new Properties();
            props.load(fs);
            return props;
        }
        catch (IOException e) {
            throw new BdException(e.getMessage());
        }
    }

    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                throw new BdException(e.getMessage());
            }
        }
    }

    public static void closeStatement(Statement st) {
        if (st != null) {
            try {
                st.close();
            } catch (SQLException e) {
                throw new BdException(e.getMessage());
            }
        }
    }

    public static void closeResultSet(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                throw new BdException(e.getMessage());
            }
        }
    }
}

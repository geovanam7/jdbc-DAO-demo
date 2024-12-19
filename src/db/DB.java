package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DB {

    private static Connection conn = null;

    public static Connection getConnection() {
        if (conn == null) {
            Properties props = loadProprietes();
            String url = props.getProperty("dburl");
            try {
                conn = DriverManager.getConnection(url, props); // Corrigido
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
        return conn;
    }

    public static void CloseConnection() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
    }

    private static Properties loadProprietes() {
        try (FileInputStream fs = new FileInputStream("db.proprieties")) {
            Properties props = new Properties();
            props.load(fs);
            return props;
        } catch (IOException e) {
            throw new DbException(e.getMessage());
        }
    }


       public static void closeStatement (Statement st){
        if (st != null){
            try{
                st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
}
       public static void closeResulSet (ResultSet rs){
        if (rs != null){
            try{
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
}





}


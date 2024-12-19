import db.DB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            conn = DB.getConnection();
            st = conn.createStatement();

            rs = st.executeQuery("Select * from department");

            while (rs.next()) {
                System.out.println(rs.getInt("Id") + "," + rs.getString("Name"));
                System.out.println(rs.getInt("Id") + "," + rs.getInt("Id"));


            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB.closeResulSet(rs);
            DB.closeStatement(st);
            DB.CloseConnection();
        }

    }
}
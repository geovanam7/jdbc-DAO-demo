package model.entities.dao.impl;

import db.DB;
import model.entities.Department;
import model.entities.dao.DepartmentDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DepartmentDaoJDBC implements DepartmentDao {

    private Connection conn;

    public DepartmentDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Department obj) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("INSERT INTO department (Name) VALUES (?)", Statement.RETURN_GENERATED_KEYS);

            st.setString(1, obj.getName());

            int rowsAffected = st.executeUpdate();
            if (rowsAffected > 0) {
                ResultSet rs = st.getGeneratedKeys();

                if (rs.next()) {
                    int id = rs.getInt(1);
                    obj.setId(id);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DB.closeStatement(st);

        }
    }


    @Override
    public void update(Department obj) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "UPDATE department " +
                            "SET Name = ? " +
                            "WHERE Id = ?");

            if (obj.getName() == null || obj.getId() == null) {
                throw new IllegalArgumentException("Department name or ID cannot be null.");
            }

            st.setString(1, obj.getName());
            st.setInt(2, obj.getId());

            st.executeUpdate();
        }
        catch (SQLException e) {
            throw new RuntimeException("Error while updating department: " + e.getMessage(), e);
        }
        finally {
            DB.closeStatement(st);
        }
    }


    @Override
    public void deletById(Integer id) {
        PreparedStatement st = null;

        try {
            st = conn.prepareStatement(
                    "DELETE FROM department WHERE Id = ?");

            st.setInt(1, id);

            st.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DB.closeStatement(st);

        }
    }

    @Override
    public Department findById(Integer id) {

        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement("SELECT * FROM department WHERE Id = ?");
            st.setInt(1, id);
            rs = st.executeQuery();

            if (rs.next()) {
                Department obj = new Department();
                obj.setId(rs.getInt("Id"));
                obj.setName(rs.getString("Name"));
                return obj;
            }

            return null;

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            DB.closeStatement(st);
            DB.closeResulSet(rs);

        }
    }

        @Override
        public List<Department> findAll () {
            PreparedStatement st = null;
            ResultSet rs = null;

            try {
                st = conn.prepareStatement("SELECT * FROM department ORDER BY Name");
                rs = st.executeQuery();

                List<Department> list = new ArrayList<>();

                while (rs.next()) {
                    Department obj = new Department();
                    obj.setId(rs.getInt("Id"));
                    obj.setName(rs.getString("Name"));
                    list.add(obj);
                }

                return list;

            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                DB.closeStatement(st);
                DB.closeResulSet(rs);


            }

        }

}
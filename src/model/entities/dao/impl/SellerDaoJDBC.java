package model.entities.dao.impl;

import db.DB;
import db.DbException;
import model.entities.Department;
import model.entities.Seller;
import model.entities.dao.SellerDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SellerDaoJDBC implements SellerDao {

    private Connection conn;

    // Construtor para inicializar a conexão
    public SellerDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Seller obj) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                     "INSERT INTO seller "+
                    "(Name, Email, BirthDate, BaseSalary, DepartmentId)"+
                    "VALUES"+
                    "(?, ?, ?, ?, ?)" , Statement.RETURN_GENERATED_KEYS);

            st.setString(1, obj.getName());
            st.setString(2, obj.getEmail());
            st.setDate(3,new java.sql.Date(obj.getBirthDate().getTime()));
            st.setDouble(4 ,obj.getBaseSalary());
            st.setInt(5,obj.getDepartment().getId());

            int rowsAffeted = st.executeUpdate();
             if (rowsAffeted > 0){
                 ResultSet rs = st.getGeneratedKeys();
                 if(rs.next()){
                     int id = rs.getInt(1);
                     obj.setId(id);
                 }
                 DB.closeResulSet(rs);
             }else{
                 throw new DbException("Unexpeted error no roows afeccted");
             }


        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
       finally {
            DB.closeStatement(st);
        }
    }

        @Override
        public void update(Seller obj) {
            PreparedStatement st = null;
            try {
                st = conn.prepareStatement(
                        "UPDATE seller " +
                                "SET Name = ?, Email = ?, BirthDate = ?, BaseSalary = ?, DepartmentId = ? " +
                                "WHERE Id = ?"
                );

                st.setString(1, obj.getName());
                st.setString(2, obj.getEmail());
                st.setDate(3, new java.sql.Date(obj.getBirthDate().getTime()));
                st.setDouble(4, obj.getBaseSalary());
                st.setInt(5, obj.getDepartment().getId());
                st.setInt(6, obj.getId());

                st.executeUpdate();

            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            } finally {
                DB.closeStatement(st);
            }
        }


    @Override
    public void deleteById(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("Id cannot be null");
        }

        try (PreparedStatement st = conn.prepareStatement("DELETE FROM seller WHERE Id = ?")) {
            st.setInt(1, id);
            int rowsAffected = st.executeUpdate();

            if (rowsAffected == 0) {
                throw new DbException("No record found with Id = " + id);
            }

        } catch (SQLException e) {
            throw new DbException("Error deleting seller with Id = " + id + ": " + e.getMessage());
        }
    }


    @Override
        public Seller findById (Integer id){
            PreparedStatement st = null;
            ResultSet rs = null;
            try {
                st = conn.prepareStatement(
                        "SELECT seller.*, department.Name as DepName " +
                                "FROM seller INNER JOIN department " +
                                "ON seller.DepartmentId = department.Id " +
                                "WHERE seller.Id = ?"
                );
                st.setInt(1, id);

                rs = st.executeQuery();
                if (rs.next()) {
                    Department dep = instantiateDepartment(rs);

                    Seller obj = instantiateSeller(rs, dep);

                    return obj;
                }
                return null;

            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            } finally {
                DB.closeStatement(st);
                DB.closeResulSet(rs);
            }
        }

        private Seller instantiateSeller (ResultSet rs, Department dep) throws SQLException {
            Seller obj = new Seller();
            obj.setId(rs.getInt("Id"));
            obj.setName(rs.getString("Name"));
            obj.setEmail(rs.getString("Email"));
            obj.setBaseSalary(rs.getDouble("BaseSalary"));
            obj.setBirthDate(rs.getDate("BirthDate"));
            obj.setDepartment(dep);
            return obj;
        }

        private Department instantiateDepartment (ResultSet rs) throws SQLException {
            Department dep = new Department();
            dep.setId(rs.getInt("DepartmentId"));
            dep.setName(rs.getString("DepName"));
            return dep;
        }

        public List<Seller> findByDepartment (Department department){
            PreparedStatement st = null;
            ResultSet rs = null;
            try {
                st = conn.prepareStatement(
                        "SELECT seller.*,department.Name as DepName " +
                                " FROM seller INNER JOIN department " +
                                "ON seller.DepartmentId = department.Id " +
                                " WHERE DepartmentId = ? " +
                                " ORDER BY Name"
                );
                st.setInt(1, department.getId());

                rs = st.executeQuery();

                List<Seller> list = new ArrayList<>();
                Map<Integer, Department> map = new HashMap<>();

                while (rs.next()) {

                    Department dep = map.get(rs.getInt("DepartmentId"));

                    if (dep == null) {
                        dep = instantiateDepartment(rs);
                        map.put(rs.getInt("DepartmentId"), dep);
                    }

                    Seller obj = instantiateSeller(rs, dep);

                    list.add(obj);
                }
                return list;

            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            } finally {
                DB.closeStatement(st);
                DB.closeResulSet(rs);
            }

        }


        @Override
        public List<Seller> findAll () {
            PreparedStatement st = null;
            ResultSet rs = null;
            try {
                st = conn.prepareStatement(
                        "SELECT seller.*,department.Name as DepName " +
                                " FROM seller INNER JOIN department " +
                                "ON seller.DepartmentId = department.Id " +
                                " ORDER BY Name"
                );

                rs = st.executeQuery();

                List<Seller> list = new ArrayList<>();
                Map<Integer, Department> map = new HashMap<>();

                while (rs.next()) {

                    Department dep = map.get(rs.getInt("DepartmentId"));

                    if (dep == null) {
                        dep = instantiateDepartment(rs);
                        map.put(rs.getInt("DepartmentId"), dep);
                    }

                    Seller obj = instantiateSeller(rs, dep);

                    list.add(obj);
                }
                return list;

            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            } finally {
                DB.closeStatement(st);
                DB.closeResulSet(rs);
            }

        }
    }


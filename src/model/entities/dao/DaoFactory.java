package model.entities.dao;

import db.DB;
import model.entities.dao.impl.DepartmentDaoJDBC;
import model.entities.dao.impl.SellerDaoJDBC;

public class DaoFactory {

    public static SellerDao createdSellerDao(){
        return new SellerDaoJDBC(DB.getConnection());
    }
    public static DepartmentDao createdDepartmentDao(){
        return new DepartmentDaoJDBC(DB.getConnection());
    }
}

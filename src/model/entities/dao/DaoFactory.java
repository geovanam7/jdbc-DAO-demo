package model.entities.dao;

import model.entities.dao.impl.SellerDaoJDBC;

public class DaoFactory {

    public static SellerDao createdSellerDao(){
        return new SellerDaoJDBC();
    }
}

import db.DB;
import model.entities.Department;
import model.entities.Seller;
import model.entities.dao.DaoFactory;
import model.entities.dao.SellerDao;

import java.sql.*;
import java.util.Date;

public class Main {
    public static void main(String[] args) {



        SellerDao sellerDao = DaoFactory.createdSellerDao();
        System.out.println(" === TEST 1: seller findById");

        Seller seller = sellerDao.findById(18);

        System.out.println(seller);


    }
}
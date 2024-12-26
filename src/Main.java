import db.DB;
import model.entities.Department;
import model.entities.Seller;
import model.entities.dao.DaoFactory;
import model.entities.dao.SellerDao;
import model.entities.dao.impl.SellerDaoJDBC;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {



        SellerDao sellerDao = DaoFactory.createdSellerDao();
        System.out.println(" ====== TEST 2: seller findById ======");

        Seller seller = sellerDao.findById(19);
        System.out.println(seller);

        System.out.println(" ====== TEST 2: seller findById ======");
        Department department = new Department(2,null);

        List<Seller> sel = sellerDao.findByDepartment(department);

        for (Seller obj: sel){
            System.out.println (obj);
      }

    }
}
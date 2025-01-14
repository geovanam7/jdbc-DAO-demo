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
        System.out.println(" ====== TEST 1: seller findById ======");

        Seller seller = sellerDao.findById(19);
        System.out.println(seller);

        System.out.println(" - - - - - - - - - - - - - ");


        System.out.println(" ====== TEST 2: seller findByDepartment ======");
        Department department = new Department(2, null);

        List<Seller> sel = sellerDao.findByDepartment(department);

        for (Seller obj : sel) {
            System.out.println(obj);
        }
        System.out.println("  - - - - - - - - - - - - - - - -");

        System.out.println(" ====== TEST 3: seller findAll ====== ");
        sel = sellerDao.findAll();


        for (Seller obj : sel) {
            System.out.println(obj);


        }
        System.out.println(" - - - - - - - - - - - - - - - - -  -");

       /* System.out.println(" ====== TEST 4: seller Insert ====== ");
        Seller newSeller = new Seller(null,"Greg", "greg@email.com", 4000.0, new Date(), department);
        sellerDao.insert(newSeller);
        System.out.println("Inserted! New id = " + newSeller.getId());
        */

        System.out.println(" - - - - - - - - - - - - - - - - -  -");

        System.out.println(" ====== TEST 5: seller Update ====== ");
        seller = sellerDao.findById(7);

        if (seller == null) {
            System.out.println("Seller with id 1 not found!");
        } else {
            seller.setName("Martha Waine");
            sellerDao.update(seller);
            System.out.println("---- UPDATE COMPLETED ----- ");
        }


    }
}
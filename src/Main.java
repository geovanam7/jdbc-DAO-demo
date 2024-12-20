import db.DB;
import model.entities.Department;
import model.entities.Seller;

import java.sql.*;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        Department obj = new Department(1, "Books");
        System.out.println(obj);

        Seller seller = new Seller(21,"bob", "bob@gmail.com", 2090.0,new Date(),obj);
        System.out.println(seller);

    }
}
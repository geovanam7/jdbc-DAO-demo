package Application;

import model.entities.Department;
import model.entities.dao.DaoFactory;
import model.entities.dao.DepartmentDao;
import model.entities.dao.SellerDao;

import java.util.List;
import java.util.Scanner;

public class Program2 {
    public static void main(String[] args) {

        DepartmentDao departmentDao = DaoFactory.createdDepartmentDao();
        Scanner sc = new Scanner(System.in);

        System.out.println("------- TEST 1: insert ---------");
        Department NewDepartment = new Department(null, "music");
        departmentDao.insert(NewDepartment);
        System.out.println("Inserted! New id: " + NewDepartment.getId());

        System.out.println("------- TEST 2: update ---------");
        Department dep = departmentDao.findById(1);
        if (dep == null) {
            System.out.println("Department with ID 1 not found!");
        } else {
            dep.setName("Food");
            departmentDao.update(dep);
            System.out.println("Update completed!");
        }

        System.out.println("------- TEST 3: deleteById --------");
        System.out.println("Enter id to delete: ");
        int id = sc.nextInt();
        Department dep2 = departmentDao.findById(id);
        if (dep2 == null) {
            System.out.println("Department with ID " + id + " not found!");
        } else {
            departmentDao.deletById(dep2.getId());
            System.out.println("Delete completed!");
        }

        System.out.println("------- TEST 4: findById ---------");
        Department dep3 = departmentDao.findById(3);
        if (dep3 == null) {
            System.out.println("Department with ID 3 not found!");
        } else {
            System.out.println(dep3);
        }

        System.out.println("\n=== TEST 5: findAll =======");
        List<Department> list = departmentDao.findAll();
        for (Department d : list) {
            System.out.println(d);
        }

        sc.close();
    }
}

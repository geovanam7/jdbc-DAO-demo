package model.entities.dao;

import model.entities.Department;

import java.util.List;

public interface DepartmentDao {
     void insert(Department obj);
     void update(Department obj);
     void deletById(Integer id);
     Department findBy(Integer id);
     List<Department> findAll();


}

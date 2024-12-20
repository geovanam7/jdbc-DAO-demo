package model.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Seller implements Serializable {

    private String email;
    private String name;
    private Double BaseSalary;
    private Integer Id;
    private Date BirthDate;
    private Department department;
    public Seller(Integer Id,String name, String email, Double baseSalary, Date birthDate, Department  department) {
        this.Id=Id;
        this.name=name;
        this.email = email;
        this.BaseSalary = baseSalary;
        this.BirthDate = birthDate;
        this.department = department;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public Double getBaseSalary() {
        return BaseSalary;
    }

    public void setBaseSalary(Double baseSalary) {
        BaseSalary = baseSalary;
    }

    public Date getBirthDate() {
        return BirthDate;
    }

    public void setBirthDate(Date birthDate) {
        BirthDate = birthDate;
    }

    @Override
    public boolean equals(Object o) {       //Apenas Id
        if (this == o) return true;
        if (!(o instanceof Seller seller)) return false;
        return Objects.equals(getId(), seller.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "Seller " +
                "email " + email + '\'' +
                " name " + name + '\'' +
                " BaseSalary " + BaseSalary +
                ", Id " + Id +
                ", BirthDate " + BirthDate;
    }
}


package com.example.ecf4gestionemployes.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    @OneToMany(mappedBy = "department", cascade = CascadeType.REMOVE)
    private List<Employee> employees;


    public Department() {
    }

    public Department(Long id, String name, String description) {
        this.id = id;
        this.name = name;

    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

}

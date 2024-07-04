package com.example.ecf4gestionemployes.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Position {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;

    @OneToMany(mappedBy = "position")
    private List<Employee> employees;

    public Position(String title) {
        this.title = title;
    }

    public Position() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

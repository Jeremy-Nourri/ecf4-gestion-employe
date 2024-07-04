package com.example.ecf4gestionemployes.repository;

import com.example.ecf4gestionemployes.model.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public abstract class BaseRepository<T> {

    protected StandardServiceRegistry registre;

    protected SessionFactory sessionFactory;

    protected Session session;

    public BaseRepository() {
        registre = new StandardServiceRegistryBuilder().configure().build();
        sessionFactory = new MetadataSources(registre).buildMetadata().buildSessionFactory();
    }
    public abstract T add(T entity);

    public abstract T findById(Long id);

    public abstract T update(T entity);

    public abstract boolean delete(Long id);

    public abstract List<T> findAll();

}
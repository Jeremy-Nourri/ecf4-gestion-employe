package com.example.ecf4gestionemployes.repository;

import com.example.ecf4gestionemployes.model.Department;

import org.hibernate.query.Query;
import org.hibernate.Transaction;

import java.util.List;

public class DepartmentRepository extends BaseRepository<Department> {

    public DepartmentRepository() {
        super();
    }

    @Override
    public List<Department> findAll() {
        Transaction transaction = null;
        List<Department> departments = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Query<Department> query = session.createQuery("from Department", Department.class);
            departments = query.getResultList();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return departments;
    }

    @Override
    public Department add(Department entity) {
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.persist(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return entity;
    }

    @Override
    public Department findById(Long id) {
        Transaction transaction = null;
        Department department = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            department = session.get(Department.class, id);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return department;
    }

    @Override
    public Department update(Department entity) {
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.merge(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return entity;
    }

    @Override
    public boolean delete(Long id) {
        Transaction transaction = null;
        boolean result = false;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Department department = session.get(Department.class, id);
            session.remove(department);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return result;
    }
}


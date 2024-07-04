package com.example.ecf4gestionemployes.repository;

import com.example.ecf4gestionemployes.exception.EmployeeNotFoundException;
import com.example.ecf4gestionemployes.model.Employee;
import org.hibernate.Hibernate;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class EmployeeRepository extends BaseRepository<Employee> {

    public EmployeeRepository() {
        super();
    }


    @Override
    public Employee add(Employee entity) {
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
    public Employee findById(Long id) {
        Transaction transaction = null;
        Employee employee = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            employee = session.get(Employee.class, id);
            if (employee == null) {
                throw new EmployeeNotFoundException("Aucun employé trouvé avec l'id " + id);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return employee;
    }

    @Override
    public Employee update(Employee entity) {
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
            Employee employee = session.get(Employee.class, id);
            session.remove(employee);
            transaction.commit();
            result = true;
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

    @Override
    public List<Employee> findAll() {
        Transaction transaction = null;
        List<Employee> employees = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Query<Employee> query = session.createQuery("from Employee", Employee.class);
            employees = query.getResultList();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return employees;
    }

    // get all employees by department
    public List<Employee> findAllByDepartment(Long id) {
        Transaction transaction = null;
        List<Employee> employees = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Query<Employee> query = session.createQuery("from Employee where department_id = :id", Employee.class);
            query.setParameter("id", id);
            employees = query.getResultList();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return employees;
    }
}

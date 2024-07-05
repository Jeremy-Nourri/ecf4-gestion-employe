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


    public List<Employee> findAllByDepartment(String name) {
        Transaction transaction = null;
        List<Employee> employees = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Query<Employee> query = session.createQuery("SELECT e FROM Employee e WHERE e.department.name = :name", Employee.class);
            query.setParameter("name", name);
            employees = query.getResultList();
            for (Employee employee : employees) {
                Hibernate.initialize(employee.getDepartment().getEmployees());
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
        return employees;
    }

    public List<Employee> findAllByPosition(String title) {
        Transaction transaction = null;
        List<Employee> employees = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Query<Employee> query = session.createQuery("SELECT e FROM Employee e WHERE e.position.title = :title", Employee.class);
            query.setParameter("title", title);
            employees = query.getResultList();
            for (Employee employee : employees) {
                Hibernate.initialize(employee.getPosition().getEmployees());
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
        return employees;
    }

    public List<Employee> searchEmployeeByFirstNameAndLastName(String firstName, String lastName) {
        Transaction transaction = null;
        List<Employee> employees = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Query<Employee> query = session.createQuery("SELECT e FROM Employee e WHERE e.firstName = :firstName AND e.lastName = :lastName", Employee.class);
            query.setParameter("firstName", firstName);
            query.setParameter("lastName", lastName);
            employees = query.getResultList();
//            for (Employee employee : employees) {
//                Hibernate.initialize(employee.getDepartment().getEmployees());
//                Hibernate.initialize(employee.getPosition().getEmployees());
//            }
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

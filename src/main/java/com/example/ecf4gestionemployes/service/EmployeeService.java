package com.example.ecf4gestionemployes.service;

import com.example.ecf4gestionemployes.exception.EmployeeNotFoundException;
import com.example.ecf4gestionemployes.model.Employee;
import com.example.ecf4gestionemployes.repository.EmployeeRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.Locale;

@ApplicationScoped
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService() {
        this.employeeRepository = new EmployeeRepository();
    }

    public Employee saveEmployee(Employee employee) {
        employeeRepository.add(employee);
        return employee;
    }

    public Employee getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id);
        if (employee == null) {
            throw new EmployeeNotFoundException("Employee with id " + id + " not found");
        }
        return employee;
    }

    public Employee updateEmployee(Employee employee) {

        return employeeRepository.update(employee);
    }

    public boolean deleteEmployee(Long id) {
        Employee employee = employeeRepository.findById(id);
        boolean isDeleted = employeeRepository.delete(id);
        if (!isDeleted) {
            throw new RuntimeException("Employee not deleted");
        }
        return isDeleted;
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public List<Employee> getEmployeesByDepartment(String name) {

        return employeeRepository.findAllByDepartment(name.toLowerCase());
    }

    public List<Employee> getEmployeesByPosition(String name) {
        return employeeRepository.findAllByPosition(name.toLowerCase());
    }

    public List<Employee> getEmployeesByFirstNameAndLastName(String firstName, String lastName) {
        return employeeRepository.searchEmployeeByFirstNameAndLastName(firstName.toLowerCase(), lastName.toLowerCase());
    }


}

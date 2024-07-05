package com.example.ecf4gestionemployes.service;

import com.example.ecf4gestionemployes.exception.DepartmentNotFoundException;
import com.example.ecf4gestionemployes.model.Department;
import com.example.ecf4gestionemployes.repository.DepartmentRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentService() {
        this.departmentRepository = new DepartmentRepository();
    }

    public Department saveDepartment(Department department) {
        departmentRepository.add(department);
        return department;
    }

    public Department getDepartmentById(Long id) {
        return departmentRepository.findById(id);
    }

    public Department getDepartmentByName(String name) {
        return departmentRepository.findByName(name);
    }

    public void updateDepartment(Department department) {
        departmentRepository.update(department);
    }

    public void deleteDepartment(Long id) {
        Department department = departmentRepository.findById(id);
        if (department == null) {
            throw new RuntimeException("Department not found");
        }
        boolean isDeleted = departmentRepository.delete(id);
        if (!isDeleted) {
            throw new RuntimeException("Department not deleted");
        }
    }

    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

}

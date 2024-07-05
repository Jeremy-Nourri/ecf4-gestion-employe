package com.example.ecf4gestionemployes.rest;

import com.example.ecf4gestionemployes.exception.EmployeeNotFoundException;
import com.example.ecf4gestionemployes.model.Department;
import com.example.ecf4gestionemployes.model.Employee;
import com.example.ecf4gestionemployes.model.Position;
import com.example.ecf4gestionemployes.service.DepartmentService;
import com.example.ecf4gestionemployes.service.EmployeeService;
import com.example.ecf4gestionemployes.service.PositionService;
import com.example.ecf4gestionemployes.utils.Views;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.Locale;

@Path("/employees")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EmployeeResource {

    @Inject
    private EmployeeService employeeService;
    @Inject
    private DepartmentService departmentService;
    @Inject
    private PositionService positionService;

    @GET
    public String getEmployees() {
        return "Bienvenue sur l'API de gestion des employés";
    }

    @GET
    @Path("/{id}")
    public Employee getEmployeeById(@PathParam("id") Long id) {
        Employee employee = employeeService.getEmployeeById(id);
        if (employee == null) {
            throw new EmployeeNotFoundException("Employee with id " + id + " not found");
        }
        return employee;
    }

    @GET
    @Path("/all")
    @JsonView(Views.Public.class)
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @POST
    @Path("/create")
    public Employee createEmployee(Employee employee) {

        try {

        Department department = departmentService.getDepartmentByName(employee.getDepartment().getName().toLowerCase());
        if (department == null) {
            department = departmentService.saveDepartment(employee.getDepartment());
        }
        employee.setDepartment(department);

        Position position = positionService.getPositionByTitle(employee.getPosition().getTitle().toLowerCase());
        if (position == null) {
            position = positionService.savePosition(employee.getPosition());
        }
        employee.setPosition(position);

        employeeService.saveEmployee(employee);

        return employee;
        } catch (Exception e) {
            throw new RuntimeException("Error creating employee");
        }
    }

    @PUT
    @Path("/update/{id}")
    public Employee updateEmployee(@PathParam("id") Long id, Employee employee) {
        Employee employeeToUpdate = employeeService.getEmployeeById(id);

        try {
            Department department = departmentService.getDepartmentByName(employee.getDepartment().getName().toLowerCase());
            if (department == null) {
                department = departmentService.saveDepartment(employee.getDepartment());
            }
            employeeToUpdate.setDepartment(department);

            Position position = positionService.getPositionByTitle(employee.getPosition().getTitle().toLowerCase());
            if (position == null) {
                position = positionService.savePosition(employee.getPosition());
            }
            employeeToUpdate.setPosition(position);

            employeeToUpdate.setFirstName(employee.getFirstName());
            employeeToUpdate.setLastName(employee.getLastName());
            employeeToUpdate.setEmail(employee.getEmail());
            employeeToUpdate.setPhone(employee.getPhone());

            return employeeService.updateEmployee(employeeToUpdate);

        } catch (Exception e) {
            throw new RuntimeException("Error updating employee");
        }
    }

    @DELETE
    @Path("/delete/{id}")
    public String deleteEmployee(@PathParam("id") Long id) {
        if (employeeService.deleteEmployee(id)) {
            return "Employee with id " + id + " deleted";
        } else {
            return "Employee with id " + id + " not found";
        }
    }

    @GET
    @Path("/department/{name}")
    public List<Employee> getEmployeesByDepartment(@PathParam("name") String name) {
        return employeeService.getEmployeesByDepartment(name.toLowerCase());
    }

    @GET
    @Path("/position/{title}")
    public List<Employee> getEmployeesByPosition(@PathParam("title") String title) {
        return employeeService.getEmployeesByPosition(title.toLowerCase());
    }

    @GET
    @Path("/search/{lastName}_{firstName}")
    public List<Employee> searchEmployeeByFirstNameAndLastName(@PathParam("firstName") String firstName, @PathParam("lastName") String lastName) {
        return employeeService.getEmployeesByFirstNameAndLastName(firstName.toLowerCase(), lastName.toLowerCase());
    }



}

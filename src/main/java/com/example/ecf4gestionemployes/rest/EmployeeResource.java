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
    public Response createEmployee(Employee employee) {
        try {
        Department department = null;
        Position position = null;
        department = departmentService.getDepartmentById(employee.getDepartment().getId());
        if (department == null) {
            department = departmentService.saveDepartment(employee.getDepartment());
        }
        employee.setDepartment(department);

        position = positionService.getPositionById(employee.getPosition().getId());
        if (position == null) {
            position = positionService.savePosition(employee.getPosition());
        }
        employee.setPosition(position);

        employeeService.saveEmployee(employee);

        return Response.status(Response.Status.CREATED)
                .entity(employee)
                .build();
        } catch (Exception e) {
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity("Error creating employee: " + e.getMessage())
                .build();
    }

    }

    @PUT
    @Path("/{id}")
    public void updateEmployee(@PathParam("id") Long id, Employee employee) {
        employee.setId(id);
        employeeService.updateEmployee(employee);
    }

    @DELETE
    @Path("/{id}")
    public String deleteEmployee(@PathParam("id") Long id) {
        if (employeeService.deleteEmployee(id)) {
            return "Employee with id " + id + " deleted";
        } else {
            return "Employee with id " + id + " not found";
        }
    }


}

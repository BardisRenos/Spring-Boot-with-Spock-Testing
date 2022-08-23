package org.example.testExample.controller;

import lombok.RequiredArgsConstructor;
import org.example.testExample.exception.EmployeeNotFoundException;
import org.example.testExample.resources.Employee;
import org.example.testExample.service.Implementation.EmployeeServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeServiceImpl employeeService;

    @GetMapping(value = "/employees")
    @ResponseStatus(HttpStatus.OK)
    public List<Employee> getEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping(value = "employee/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Employee getEmployeeById(@PathVariable(value = "id") Long id) throws EmployeeNotFoundException {
        return employeeService.getEmployeeById(id);
    }

    @GetMapping(value = "employee")
    @ResponseStatus(HttpStatus.OK)
    public List<Employee> getEmployeesByLastName(@RequestParam(value = "lastName") String lastName) throws EmployeeNotFoundException {
        return employeeService.getEmployeeByLastName(lastName);

    }
}
